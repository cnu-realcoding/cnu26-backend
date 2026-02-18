package com.inspire12.backend.repository;

import com.inspire12.backend.dto.User;

import java.util.List;
import java.util.Optional;

// Repository 인터페이스: 데이터 접근 계약을 정의
// 구현체를 교체하면 (Memory → DB) 서비스 코드 변경 없이 저장소 교체 가능
public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Long id);

    List<User> findByNameContaining(String name);

    User save(User user);

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}
