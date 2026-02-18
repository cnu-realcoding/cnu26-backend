package com.inspire12.backend.repository;

import com.inspire12.backend.dto.User;

import java.util.List;

// Before: 모든 CRUD 메서드를 직접 선언
// After : CrudRepository<User, Long> 을 상속하면 기본 CRUD 는 자동 포함
//
// 비교: Spring Data JPA 에서는 아래처럼 쓴다
//   public interface UserRepository extends JpaRepository<User, Long> { ... }
//
// extends 만으로 findAll, findById, save, deleteById 등이 제공되고,
// 추가 메서드만 여기에 선언하면 된다
public interface UserRepository extends CrudRepository<User, Long> {

    // 커스텀 쿼리 메서드: 이름으로 검색
    // Spring Data JPA 에서는 메서드 이름만으로 쿼리가 자동 생성됨
    List<User> findByNameContaining(String name);
}
