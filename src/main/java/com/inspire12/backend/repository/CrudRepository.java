package com.inspire12.backend.repository;

import java.util.List;
import java.util.Optional;

// Spring Data JPA 의 CrudRepository 를 흉내낸 제네릭 인터페이스
// T: 엔티티 타입, ID: PK 타입
//
// TODO: 제네릭 타입 파라미터 <T, ID> 를 선언하세요
public interface CrudRepository<T, ID> {

    // TODO: 전체 조회 메서드를 선언하세요
    // 힌트: List<T> findAll();

    // TODO: ID 로 단건 조회 메서드를 선언하세요
    // 힌트: Optional<T> findById(ID id);

    // TODO: 엔티티 저장 (생성 + 수정) 메서드를 선언하세요
    // 힌트: T save(T entity);

    // TODO: ID 로 삭제 메서드를 선언하세요

    // TODO: ID 로 존재 여부 확인 메서드를 선언하세요

    // TODO: 전체 개수 반환 메서드를 선언하세요
}
