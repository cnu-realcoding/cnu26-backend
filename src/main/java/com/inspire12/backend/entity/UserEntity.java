package com.inspire12.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// JPA Entity: DB 테이블과 매핑되는 클래스
//
// 비교: DTO (record) vs Entity (class)
// - DTO (User record)  : 불변, 간단, Controller ↔ Client 데이터 전달용
// - Entity (UserEntity) : 가변, JPA 가 관리, DB ↔ 애플리케이션 매핑용
//
// JPA Entity 규칙:
// 1. @Entity 필수
// 2. @Id 로 기본키 지정
// 3. 기본 생성자(no-args constructor) 필수
// 4. record 는 사용할 수 없음 (JPA 가 내부에서 객체를 생성/변경해야 하므로)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    // JPA 가 내부에서 사용하는 기본 생성자
    protected UserEntity() {
    }

    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter / Setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
