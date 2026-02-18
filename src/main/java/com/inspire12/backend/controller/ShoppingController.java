package com.inspire12.backend.controller;

import com.inspire12.backend.dto.ShoppingItem;
import com.inspire12.backend.service.NaverShoppingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: 네이버 쇼핑 검색 컨트롤러를 완성하세요
// 1. @RestController, @RequestMapping("/shop") 어노테이션 추가
// 2. NaverShoppingService 를 주입받는 생성자 작성
// 3. GET /shop/search 엔드포인트 구현
//
// 외부 API 연동 흐름:
//   Client → ShoppingController → NaverShoppingService → RestClient → 네이버 API
@Tag(name = "Shopping", description = "네이버 쇼핑 검색 API")
@RestController
@RequestMapping("/shop")
public class ShoppingController {

    private final NaverShoppingService naverShoppingService;

    public ShoppingController(NaverShoppingService naverShoppingService) {
        this.naverShoppingService = naverShoppingService;
    }

    @Operation(summary = "상품 검색", description = "네이버 쇼핑 API 를 통해 상품을 검색합니다")
    @GetMapping("/search")
    public List<ShoppingItem> searchProducts(
            @Parameter(description = "검색 키워드", example = "맥북")
            @RequestParam String query,
            @Parameter(description = "검색 결과 수 (1~100)")
            @RequestParam(defaultValue = "10") int display) {
        return naverShoppingService.searchProducts(query, display);
    }
}
