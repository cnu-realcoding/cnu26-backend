package com.inspire12.backend.service;

import com.inspire12.backend.dto.User;
import com.inspire12.backend.exception.InvalidRequestException;
import com.inspire12.backend.exception.UserNotFoundException;
import com.inspire12.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: @Service 애노테이션을 확인하세요 (스프링 빈 등록)
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    // TODO: UserRepository 를 필드로 선언하고 생성자 주입을 받으세요
    // 힌트:
    // private final UserRepository userRepository;
    // public UserService(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

    public List<User> getAllUsers() {
        // TODO: userRepository.findAll() 을 호출하세요
        return List.of();
    }

    public User getUserById(Long id) {
        // TODO: userRepository.findById(id) 로 조회하고
        // 없으면 .orElseThrow(() -> new UserNotFoundException(id)) 를 던지세요
        return null;
    }

    public User getUserDetail(Long id) {
        // TODO: id <= 0 이면 InvalidRequestException 을 던지세요
        // TODO: getUserById(id) 를 호출하여 반환하세요
        return null;
    }

    public List<User> searchByName(String name) {
        // TODO: userRepository.findByNameContaining(name) 을 호출하세요
        return List.of();
    }

    public User createUser(User request) {
        // TODO: userRepository.save() 로 새 유저를 저장하세요
        // 힌트: new User(null, request.name(), request.email()) 을 save
        return null;
    }

    public User updateUser(Long id, User request) {
        // TODO: existsById 로 존재 확인, 없으면 UserNotFoundException
        // TODO: userRepository.save(new User(id, ...)) 로 수정
        return null;
    }

    public void deleteUser(Long id) {
        // TODO: existsById 로 존재 확인, 없으면 UserNotFoundException
        // TODO: userRepository.deleteById(id) 호출
    }

    public long getUserCount() {
        // TODO: userRepository.count() 반환
        return 0;
    }
}
