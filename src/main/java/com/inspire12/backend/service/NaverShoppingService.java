package com.inspire12.backend.service;

import com.inspire12.backend.dto.NaverShoppingResponse;
import com.inspire12.backend.dto.ShoppingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

// 네이버 쇼핑 검색 API 호출 서비스
//
// 외부 API 호출 흐름:
//   Controller → Service → RestClient → 네이버 API 서버
//
// RestClient: Spring 6.1+ 에서 도입된 동기 HTTP 클라이언트
// - RestTemplate 의 후속 (더 간결한 API)
// - WebClient 는 비동기/리액티브, RestClient 는 동기 방식
@Service
public class NaverShoppingService {

    private static final Logger log = LoggerFactory.getLogger(NaverShoppingService.class);

    private final RestClient restClient;

    // application.properties 에서 값을 주입받음
    // @Value 는 설정 파일의 값을 필드에 바인딩하는 어노테이션
    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    public NaverShoppingService() {
        // RestClient 생성: 기본 URL 을 지정하면 이후 호출에서 경로만 사용 가능
        this.restClient = RestClient.builder()
                .baseUrl("https://openapi.naver.com")
                .build();
    }

    // 네이버 쇼핑 검색 API 호출
    // GET https://openapi.naver.com/v1/search/shop.json?query=키워드&display=개수
    public List<ShoppingItem> searchProducts(String query, int display) {
        log.info("네이버 쇼핑 검색 요청 - query: {}, display: {}", query, display);

        NaverShoppingResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/shop.json")
                        .queryParam("query", query)
                        .queryParam("display", display)
                        .build())
                // 네이버 API 인증 헤더
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                // 응답을 NaverShoppingResponse 로 변환
                .retrieve()
                .body(NaverShoppingResponse.class);

        if (response == null || response.items() == null) {
            log.warn("네이버 쇼핑 검색 결과 없음 - query: {}", query);
            return List.of();
        }

        log.info("네이버 쇼핑 검색 완료 - 총 {}건 중 {}건 반환", response.total(), response.items().size());
        return response.items();
    }
}
