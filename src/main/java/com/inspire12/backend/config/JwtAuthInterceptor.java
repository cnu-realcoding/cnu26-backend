package com.inspire12.backend.config;

import com.inspire12.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

// JWT 인증 인터셉터
// WebMvcConfig 에서 등록한 경로에만 적용됨
// 인터셉터 예외는 @RestControllerAdvice 를 경유하지 않으므로 직접 401 응답 작성
@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 없음
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            writeUnauthorized(response, "Authorization 헤더가 필요합니다");
            return false;
        }

        String token = authorization.substring("Bearer ".length());

        // 토큰 검증 실패
        if (!JwtUtil.validateToken(token)) {
            writeUnauthorized(response, "유효하지 않은 토큰입니다");
            return false;
        }

        // 검증 성공 → userId 를 request attribute 로 전달
        Long userId = JwtUtil.getUserIdFromToken(token);
        request.setAttribute("userId", userId);
        return true;
    }

    private void writeUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                """
                {"status":401,"error":"Unauthorized","message":"%s"}""".formatted(message)
        );
    }
}
