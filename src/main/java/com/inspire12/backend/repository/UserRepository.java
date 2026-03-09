package com.inspire12.backend.repository;

import com.inspire12.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Page, Pageable import 를 추가하세요
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

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
//
// Pageable 지원:
//   JpaRepository 는 PagingAndSortingRepository 를 상속하므로
//   findAll(Pageable) 이 기본 제공됨.
//   커스텀 쿼리 메서드에도 Pageable 파라미터를 추가하면 Page 로 반환 가능
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 커스텀 쿼리 메서드: 이름으로 검색
    // Spring Data JPA 가 메서드 이름을 분석해서 SQL 을 자동 생성함
    // → SELECT * FROM users WHERE name LIKE '%keyword%'
    List<UserEntity> findByNameContaining(String name);

    // TODO: Pageable 을 지원하는 findByNameContaining 메서드를 추가하세요
    // 힌트: 반환 타입을 Page<UserEntity> 로, 파라미터에 Pageable 을 추가하면
    //       Spring Data JPA 가 LIMIT/OFFSET + COUNT 쿼리를 자동 생성합니다
    // Page<UserEntity> findByNameContaining(????, ????);
}
