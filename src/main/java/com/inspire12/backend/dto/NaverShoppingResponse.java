package com.inspire12.backend.dto;

import java.util.List;

// 네이버 쇼핑 검색 API 의 전체 응답 구조
// {
//   "lastBuildDate": "...",
//   "total": 12345,
//   "start": 1,
//   "display": 10,
//   "items": [ { ... }, { ... } ]
// }
public record NaverShoppingResponse(
        String lastBuildDate,   // 검색 결과 생성 시간
        int total,              // 전체 검색 결과 수
        int start,              // 검색 시작 위치
        int display,            // 한 번에 표시할 검색 결과 수
        List<ShoppingItem> items // 상품 목록
) {
}
