package com.inspire12.backend.dto;

// 네이버 쇼핑 검색 API 응답의 개별 상품 정보
// JSON 필드명과 동일하게 record 필드를 정의하면 자동 매핑됨
public record ShoppingItem(
        String title,       // 상품명 (HTML 태그 포함 가능)
        String link,        // 상품 상세 URL
        String image,       // 상품 이미지 URL
        String lprice,      // 최저가
        String hprice,      // 최고가
        String mallName,    // 쇼핑몰 이름
        Long productId,     // 상품 ID
        String productType, // 상품 타입
        String brand,       // 브랜드
        String maker,       // 제조사
        String category1,   // 카테고리 1
        String category2,   // 카테고리 2
        String category3,   // 카테고리 3
        String category4    // 카테고리 4
) {
}
