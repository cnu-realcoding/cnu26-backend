package com.inspire12.backend.config;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

// 커스텀 Health Indicator
// /actuator/health 에서 "user" 항목으로 표시됨
// TODO: @Component 애노테이션을 확인하세요 (빈으로 등록해야 Actuator 가 인식)
@Component
public class UserHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean isHealthy = true;

        // TODO: isHealthy 에 따라 Health.up() 또는 Health.down() 을 반환하세요
        // 힌트: Health.up().withDetail("키", "값").build()
        // withDetail 로 "userService" → "유저 서비스 정상", "userCount" → 3 을 추가하세요
        return Health.up().build();
    }
}
