package com.inspire12.backend.service;

import com.inspire12.backend.dto.OrderRequest;
import com.inspire12.backend.dto.OrderResponse;
import com.inspire12.backend.entity.OrderEntity;
import com.inspire12.backend.exception.InvalidRequestException;
import com.inspire12.backend.exception.UserNotFoundException;
import com.inspire12.backend.repository.OrderRepository;
import com.inspire12.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: 주문 Service 를 완성하세요
//
// "유저 체크" 의 의미:
//   1. 인증 (Authentication): JWT 토큰 → Interceptor 가 담당 (이미 완료)
//   2. 유효성 (Validation): 유저가 DB 에 존재하는지 → 여기서 확인
@Service
@Transactional(readOnly = true)
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderResponse createOrder(Long userId, OrderRequest request) {
        // TODO 1: 유저 존재 여부 확인
        // if (!userRepository.existsById(userId)) {
        //     throw new UserNotFoundException(userId);
        // }

        // TODO 2: 요청 데이터 검증 (수량, 가격)

        // TODO 3: OrderEntity 생성 및 저장

        // TODO 4: DTO 변환 후 반환
        return null;
    }

    public Page<OrderResponse> getOrdersByUserId(Long userId, Pageable pageable) {
        // TODO: 유저 확인 후 주문 목록 조회
        return null;
    }

    private OrderResponse toDto(OrderEntity entity) {
        // TODO: Entity → DTO 변환
        // totalPrice = price * quantity
        return null;
    }
}
