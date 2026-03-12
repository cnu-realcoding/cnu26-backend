package com.inspire12.backend.controller;

import com.inspire12.backend.dto.OrderRequest;
import com.inspire12.backend.dto.OrderResponse;
import com.inspire12.backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

// TODO: 주문 Controller 를 완성하세요
//
// 프론트엔드 화면 흐름 → API 매핑:
//   4. 구매      → POST /orders   (신규, JWT 필요)
//   5. 주문 목록 → GET  /orders   (신규, JWT 필요)
//
// 포인트: @RequestAttribute("userId") 로 JWT 에서 추출한 userId 를 받음
@Tag(name = "Order", description = "주문 API")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // TODO: POST /orders - 구매 API 를 구현하세요
    // 힌트: UserController.createUser() 와 비슷한 패턴
    //   - @RequestAttribute("userId") 로 JWT 에서 userId 추출
    //   - @RequestBody OrderRequest 로 주문 정보 수신
    //   - ResponseEntity.created() 로 201 응답

    // TODO: GET /orders - 내 주문 목록 API 를 구현하세요
    // 힌트: UserController.getUsers() 와 비슷한 패턴
    //   - @RequestAttribute("userId") 로 JWT 에서 userId 추출
    //   - Pageable 로 페이징 지원
}
