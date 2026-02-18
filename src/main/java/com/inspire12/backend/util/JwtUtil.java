package com.inspire12.backend.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

// 외부 라이브러리 없이 순수 Java 로 구현한 간단한 JWT 유틸리티
//
// JWT 구조: header.payload.signature (점으로 구분된 3개 파트)
//   - Header   : {"alg":"HS256","typ":"JWT"} → Base64URL 인코딩
//   - Payload  : {"sub":"userId","iat":발급시간,"exp":만료시간} → Base64URL 인코딩
//   - Signature: HMAC-SHA256(header.payload, secretKey) → Base64URL 인코딩
//
// 참고: 실무에서는 jjwt, auth0-java-jwt 등 라이브러리를 사용
// 여기서는 JWT 의 내부 구조를 이해하기 위해 직접 구현
public class JwtUtil {

    // 서명에 사용할 비밀키 (실무에서는 외부 설정으로 관리)
    private static final String SECRET_KEY = "my-secret-key-for-jwt-signing-must-be-long-enough";

    // 토큰 만료 시간: 1시간 (초 단위)
    private static final long EXPIRATION_SECONDS = 3600;

    // Base64URL 인코더/디코더 (URL-safe, 패딩 없음)
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    private static final Base64.Decoder decoder = Base64.getUrlDecoder();

    // ========== 토큰 생성 ==========

    // userId 를 담은 JWT 토큰을 생성
    public static String generateToken(Long userId) {
        // 1. Header: 알고리즘과 토큰 타입
        String header = """
                {"alg":"HS256","typ":"JWT"}""";

        // 2. Payload: 사용자 정보와 시간 정보
        long now = Instant.now().getEpochSecond();
        long exp = now + EXPIRATION_SECONDS;
        String payload = """
                {"sub":"%d","iat":%d,"exp":%d}""".formatted(userId, now, exp);

        // 3. Base64URL 인코딩
        String encodedHeader = encodeBase64Url(header);
        String encodedPayload = encodeBase64Url(payload);

        // 4. Signature: HMAC-SHA256(header.payload, secretKey)
        String dataToSign = encodedHeader + "." + encodedPayload;
        String signature = sign(dataToSign);

        // 5. 최종 JWT: header.payload.signature
        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    // ========== 토큰 검증 ==========

    // 토큰의 서명이 유효하고 만료되지 않았는지 확인
    public static boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return false;
            }

            // 서명 검증: 다시 서명해서 일치하는지 확인
            String dataToSign = parts[0] + "." + parts[1];
            String expectedSignature = sign(dataToSign);
            if (!expectedSignature.equals(parts[2])) {
                return false;
            }

            // 만료 시간 검증
            String payload = decodeBase64Url(parts[1]);
            long exp = extractLongFromJson(payload, "exp");
            return Instant.now().getEpochSecond() < exp;

        } catch (Exception e) {
            return false;
        }
    }

    // ========== 토큰에서 정보 추출 ==========

    // JWT payload 에서 사용자 ID (sub) 를 추출
    public static Long getUserIdFromToken(String token) {
        try {
            String[] parts = token.split("\\.");
            String payload = decodeBase64Url(parts[1]);
            String sub = extractStringFromJson(payload, "sub");
            return Long.parseLong(sub);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다");
        }
    }

    // ========== 내부 유틸 메서드 ==========

    // HMAC-SHA256 서명 생성
    private static String sign(String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(keySpec);
            byte[] signature = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return encoder.encodeToString(signature);
        } catch (Exception e) {
            throw new RuntimeException("JWT 서명 생성 실패", e);
        }
    }

    // 문자열 → Base64URL 인코딩
    private static String encodeBase64Url(String data) {
        return encoder.encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    // Base64URL → 문자열 디코딩
    private static String decodeBase64Url(String data) {
        return new String(decoder.decode(data), StandardCharsets.UTF_8);
    }

    // 간단한 JSON 파싱: "key":value 에서 long 값 추출
    // (실무에서는 Jackson ObjectMapper 를 사용)
    private static long extractLongFromJson(String json, String key) {
        String search = "\"" + key + "\":";
        int start = json.indexOf(search) + search.length();
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);
        return Long.parseLong(json.substring(start, end).trim());
    }

    // 간단한 JSON 파싱: "key":"value" 에서 문자열 값 추출
    private static String extractStringFromJson(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search) + search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
