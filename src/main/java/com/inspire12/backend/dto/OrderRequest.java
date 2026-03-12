package com.inspire12.backend.dto;

// TODO: 주문 요청 DTO 를 완성하세요
//
// 포인트: userId 를 여기에 포함하지 않는 이유는?
//   → JWT 토큰에서 서버가 직접 추출 (클라이언트가 보낸 userId 를 믿지 않음)
public record OrderRequest(
        // TODO: 필드를 추가하세요
        // Long productId,
        // String title,
        // String image,
        // int price,
        // int quantity
) {
}
