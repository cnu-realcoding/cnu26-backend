package com.inspire12.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

// TODO: 주문 Entity 를 완성하세요
// 힌트: UserEntity 와 동일한 패턴입니다
//
// 비즈니스 요구사항 → 테이블 설계:
//   "유저가 상품을 구매한다"
//     - 누가? → userId
//     - 무엇을? → productId, title, image, price
//     - 얼마나? → quantity
//     - 언제? → orderedAt
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: 필드를 추가하세요
    // private Long userId;
    // private Long productId;
    // private String title;
    // private String image;
    // private int price;
    // private int quantity;
    // private LocalDateTime orderedAt;

    protected OrderEntity() {
    }

    // TODO: 생성자를 완성하세요
    public OrderEntity(Long userId, Long productId, String title, String image, int price, int quantity) {
        // this.userId = userId;
        // this.productId = productId;
        // this.title = title;
        // this.image = image;
        // this.price = price;
        // this.quantity = quantity;
        // this.orderedAt = LocalDateTime.now();
    }

    // TODO: Getter 메서드를 추가하세요
    public Long getId() {
        return id;
    }
}
