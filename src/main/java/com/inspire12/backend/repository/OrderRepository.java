package com.inspire12.backend.repository;

import com.inspire12.backend.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: 주문 Repository 를 완성하세요
// 힌트: UserRepository 와 동일한 패턴입니다
//
// 비즈니스 요구사항:
//   "내 주문 목록을 최신순으로 보고 싶다"
//   → findBy_____OrderBy_____Desc(Long userId, Pageable pageable)
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // TODO: userId 로 주문을 조회하는 쿼리 메서드를 작성하세요
    // 최신 주문이 먼저 나오도록 orderedAt 기준 내림차순 정렬
    // Page<OrderEntity> findBy???(Long userId, Pageable pageable);
}
