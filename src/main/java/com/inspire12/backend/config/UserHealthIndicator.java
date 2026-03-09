package com.inspire12.backend.config;

import com.inspire12.backend.BackendApplication;
import com.inspire12.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

// 커스텀 Health Indicator
// /actuator/health 에서 "user" 항목으로 표시됨
//
// JPA 도입 후: DB 에서 실제 유저 수를 조회하여 헬스 체크
@Component
public class UserHealthIndicator implements HealthIndicator {
    private final static Logger log = LoggerFactory.getLogger(BackendApplication.class);
    private final UserRepository userRepository;

    public UserHealthIndicator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Health health() {
        try {
            long count = userRepository.count();
            return Health.up()
                    .withDetail("userService", "유저 서비스 정상")
                    .withDetail("userCount", count)
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("userService", "유저 서비스 장애")
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
