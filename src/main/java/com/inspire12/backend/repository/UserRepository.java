package com.inspire12.backend.repository;

import com.inspire12.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Before: 우리가 만든 CrudRepository<User, Long> 을 상속
// After : Spring Data JPA 의 JpaRepository<UserEntity, Long> 을 상속
//
// JpaRepository 를 extends 하면:
// - findAll(), findById(), save(), deleteById() 등 기본 CRUD 자동 제공
// - 구현 클래스(MemoryUserRepository)를 만들 필요 없음! Spring 이 자동 생성
// - 메서드 이름만으로 쿼리 자동 생성 (Query Method)
//
// 비교:
//   이전 단계 (직접 구현)          →  JPA (자동 구현)
//   CrudRepository<User, Long>    →  JpaRepository<UserEntity, Long>
//   MemoryUserRepository (직접)    →  Spring Data JPA 가 프록시로 자동 생성
//   findByNameContaining (직접)    →  메서드 이름으로 쿼리 자동 생성
// TODO: JpaRepository<엔티티타입, PK타입> 을 extends 하도록 변경하세요
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // TODO: 이름으로 검색하는 쿼리 메서드를 선언하세요
    // 힌트: Spring Data JPA 는 메서드 이름으로 쿼리를 자동 생성합니다
    // → SELECT * FROM users WHERE name LIKE '%keyword%'
    List<UserEntity> findByNameContaining(String name);
}
