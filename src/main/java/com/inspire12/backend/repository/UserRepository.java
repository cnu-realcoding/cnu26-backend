package com.inspire12.backend.repository;

import com.inspire12.backend.dto.User;

import java.util.List;

// TODO: CrudRepository<User, Long> 을 상속하세요
// 힌트: extends CrudRepository<User, Long>
//
// 비교: Spring Data JPA 에서는 이렇게 쓴다
//   public interface UserRepository extends JpaRepository<UserEntity, Long> { ... }
public interface UserRepository extends CrudRepository<User, Long> {

    // TODO: 이름으로 검색하는 커스텀 메서드를 선언하세요
    // 힌트: List<User> findByNameContaining(String name);
    // Spring Data JPA 에서는 메서드 이름만으로 쿼리가 자동 생성됨
}
