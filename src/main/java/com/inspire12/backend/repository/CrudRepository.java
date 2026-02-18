package com.inspire12.backend.repository;

import java.util.List;
import java.util.Optional;

// Spring Data JPA 의 CrudRepository 를 흉내낸 제네릭 인터페이스
// T: 엔티티 타입, ID: PK 타입
//
// 이 인터페이스 하나로 어떤 엔티티든 기본 CRUD 를 제공할 수 있다
// Spring Data JPA 에서는 이 인터페이스를 extends 하면 구현체가 자동 생성됨
public interface CrudRepository<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void deleteById(ID id);

    boolean existsById(ID id);

    long count();
}
