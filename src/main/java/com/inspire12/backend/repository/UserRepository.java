package com.inspire12.backend.repository;

import com.inspire12.backend.dto.User;

import java.util.List;
import java.util.Optional;

// Repository 인터페이스: 데이터 접근 계약을 정의
// 구현체를 교체하면 (Memory → DB) 서비스 코드 변경 없이 저장소 교체 가능
public interface UserRepository {

    // TODO: 아래 메서드들의 시그니처를 완성하세요

    List<User> findAll();

    Optional<User> findById(Long id);

    // TODO: 이름을 포함하는 유저 목록 검색 메서드를 선언하세요
    // 힌트: List<User> findByNameContaining(String name);

    // TODO: 유저 저장 (생성 + 수정) 메서드를 선언하세요
    // 힌트: User save(User user);

    // TODO: ID로 유저 삭제 메서드를 선언하세요

    // TODO: ID로 존재 여부 확인 메서드를 선언하세요

    long count();
}
