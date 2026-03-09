package com.inspire12.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 인터셉터 등록 + 인증이 필요한 경로 설정
//
// addPathPatterns: 인증이 필요한 경로 (화이트리스트 방식)
// 나머지 경로(/users/login, /users, /system/**, /swagger-ui/**, /actuator/** 등)는 인터셉터 미적용
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final JwtAuthInterceptor jwtAuthInterceptor;

    public WebMvcConfig(JwtAuthInterceptor jwtAuthInterceptor) {
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }

    // TODO: CORS 설정을 완성하세요
    // 프론트엔드(http://localhost:3000)에서 백엔드 API 를 호출할 수 있도록 허용합니다
    //
    // 1. addMapping("/**") - 모든 경로에 CORS 적용
    // 2. allowedOrigins - 허용할 프론트엔드 주소
    // 3. allowedMethods - 허용할 HTTP 메서드 (GET, POST, PUT, DELETE, OPTIONS)
    // 4. allowedHeaders("*") - 모든 헤더 허용
    // 5. allowCredentials(true) - 쿠키/인증 헤더 포함 허용
    // 6. maxAge(3600) - preflight 요청 캐시 시간 (초)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping(????)
        //         .allowedOrigins(????)
        //         .allowedMethods(????)
        //         .allowedHeaders(????)
        //         .allowCredentials(????)
        //         .maxAge(????);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/shop/**", "/users/me");
    }
}
