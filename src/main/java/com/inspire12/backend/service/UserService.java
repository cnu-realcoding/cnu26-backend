package com.inspire12.backend.service;

import com.inspire12.backend.dto.User;
import com.inspire12.backend.entity.UserEntity;
import com.inspire12.backend.exception.InvalidRequestException;
import com.inspire12.backend.exception.UserNotFoundException;
import com.inspire12.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Service 계층: 비즈니스 로직 담당
//
// JPA 도입 후 변경점:
// 1. Entity ↔ DTO 변환이 필요 (Controller 에는 DTO 만 노출)
// 2. @Transactional 로 트랜잭션 관리
//    - readOnly = true : 조회 전용 (성능 최적화)
//    - 기본값(readOnly = false) : 쓰기 작업
// TODO: 클래스 레벨에 @Transactional(readOnly = true) 를 추가하세요
@Service
@Transactional(readOnly = true)
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        // TODO: findAll() 결과를 Entity → DTO 로 변환하세요
        // 힌트: stream().map(this::toDto).toList()
        List<User> users = userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
        log.info("유저 목록 조회 - 총 {}명", users.size());
        return users;
    }

    public User getUserById(Long id) {
        log.debug("유저 단건 조회 - id: {}", id);
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserDetail(Long id) {
        if (id <= 0) {
            throw new InvalidRequestException("ID는 1 이상이어야 합니다. 입력값: " + id);
        }
        return getUserById(id);
    }

    public List<User> searchByName(String name) {
        log.info("유저 검색 - name: {}", name);
        List<User> result = userRepository.findByNameContaining(name).stream()
                .map(this::toDto)
                .toList();
        log.debug("유저 검색 결과 - {}건", result.size());
        return result;
    }

    // TODO: 쓰기 작업에는 @Transactional 을 추가하세요 (readOnly 아님)
    @Transactional
    public User createUser(User request) {
        log.info("유저 생성 요청 - name: {}, email: {}", request.name(), request.email());
        // TODO: User DTO → UserEntity 로 변환하여 저장하세요
        UserEntity entity = new UserEntity(request.name(), request.email());
        UserEntity saved = userRepository.save(entity);
        log.info("유저 생성 완료 - id: {}", saved.getId());
        return toDto(saved);
    }

    @Transactional
    public User updateUser(Long id, User request) {
        log.info("유저 수정 요청 - id: {}, name: {}, email: {}", id, request.name(), request.email());
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        // 기존 Entity 의 필드를 변경 (JPA 변경 감지 = Dirty Checking)
        entity.setName(request.name());
        entity.setEmail(request.email());
        // save() 를 호출하지 않아도 트랜잭션 종료 시 자동 반영되지만,
        // 명시적으로 호출하는 것이 의도가 더 명확함
        UserEntity updated = userRepository.save(entity);
        log.info("유저 수정 완료 - id: {}", id);
        return toDto(updated);
    }

    @Transactional
    public void deleteUser(Long id) {
        log.info("유저 삭제 요청 - id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        log.warn("유저 삭제 완료 - id: {}", id);
    }

    public long getUserCount() {
        return userRepository.count();
    }

    // ========== Entity ↔ DTO 변환 ==========

    // TODO: Entity → DTO 변환 메서드를 완성하세요
    private User toDto(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }
}
