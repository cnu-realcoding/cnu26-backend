package com.inspire12.backend.service;

import com.inspire12.backend.dto.User;
import com.inspire12.backend.exception.InvalidRequestException;
import com.inspire12.backend.exception.UserNotFoundException;
import com.inspire12.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

// Service 계층: 비즈니스 로직 담당
// Controller 는 요청/응답만, Service 가 실제 로직을 처리
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("유저 목록 조회 - 총 {}명", users.size());
        return users;
    }

    public User getUserById(Long id) {
        log.debug("유저 단건 조회 - id: {}", id);
        return userRepository.findById(id)
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
        List<User> result = userRepository.findByNameContaining(name);
        log.debug("유저 검색 결과 - {}건", result.size());
        return result;
    }

    public User createUser(User request) {
        log.info("유저 생성 요청 - name: {}, email: {}", request.name(), request.email());
        User saved = userRepository.save(new User(null, request.name(), request.email()));
        log.info("유저 생성 완료 - id: {}", saved.id());
        return saved;
    }

    public User updateUser(Long id, User request) {
        log.info("유저 수정 요청 - id: {}, name: {}, email: {}", id, request.name(), request.email());
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        User updated = userRepository.save(new User(id, request.name(), request.email()));
        log.info("유저 수정 완료 - id: {}", id);
        return updated;
    }

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
}
