# Step 1: 프로젝트 초기 설정

> **브랜치:** `main`

---

## 학습 목표

- Spring Boot 프로젝트의 기본 구조를 이해한다.
- `@SpringBootApplication` 어노테이션의 역할을 파악한다.
- `build.gradle`의 핵심 구성 요소(plugins, dependencies, toolchain)를 읽을 수 있다.
- `./gradlew bootRun` 명령으로 애플리케이션을 실행할 수 있다.

---

## 핵심 개념 설명

### 왜 Spring Boot인가?

Spring Framework는 강력하지만 설정이 복잡하다. Spring Boot는 이 문제를 해결하기 위해 등장했다.

- **자동 설정(Auto Configuration):** 의존성을 추가하면 관련 설정이 자동으로 적용된다.
- **내장 서버:** 별도의 WAS(Tomcat 등) 설치 없이 바로 실행 가능하다.
- **스타터 의존성:** `spring-boot-starter-*` 하나로 필요한 라이브러리를 묶어서 관리한다.
- **Opinionated Defaults:** 합리적인 기본값을 제공하여 "관례"를 따르면 설정 없이 동작한다.

### 프로젝트 구조

```
cnu26-backend/
├── build.gradle              ← 빌드 설정 (의존성, 플러그인, Java 버전)
├── settings.gradle           ← 프로젝트 이름 설정
├── gradlew / gradlew.bat     ← Gradle Wrapper (Gradle 설치 없이 실행)
├── gradle/wrapper/           ← Gradle Wrapper 설정 파일
└── src/
    ├── main/
    │   ├── java/com/inspire12/backend/
    │   │   └── BackendApplication.java   ← 메인 클래스
    │   └── resources/
    │       └── application.properties    ← 애플리케이션 설정
    └── test/
        └── java/com/inspire12/backend/
            └── BackendApplicationTests.java  ← 테스트 클래스
```

### @SpringBootApplication의 의미

이 어노테이션 하나에 세 가지 기능이 포함되어 있다:

| 어노테이션 | 역할 |
|---|---|
| `@SpringBootConfiguration` | Spring Boot 설정 클래스임을 선언 |
| `@EnableAutoConfiguration` | 클래스패스의 라이브러리를 기반으로 자동 설정 |
| `@ComponentScan` | 현재 패키지 하위의 컴포넌트를 자동 스캔 |

### build.gradle 구성 요소

| 블록 | 역할 |
|---|---|
| `plugins` | 빌드 도구 플러그인 (java, spring-boot, dependency-management) |
| `java.toolchain` | 사용할 Java 버전 지정 (21) |
| `repositories` | 의존성을 다운로드할 저장소 (Maven Central) |
| `dependencies` | 프로젝트가 사용하는 라이브러리 목록 |

---

## 주요 코드

### BackendApplication.java

```java
package com.inspire12.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
```

**핵심 포인트:**
- `SpringApplication.run()` 호출로 Spring 컨테이너를 초기화하고 애플리케이션을 시작한다.
- `main` 메서드가 Java 프로그램의 진입점이며, Spring Boot도 결국 일반 Java 프로그램이다.

### build.gradle

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '4.0.2'
    id 'io.spring.dependency-management' version '1.1.7'
}

group = 'com.inspire12'
version = '0.0.1-SNAPSHOT'
description = 'backend'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

**핵심 포인트:**
- `spring-boot-starter`: Spring Boot의 핵심 스타터. 아직 웹 기능은 포함되지 않음.
- `spring-boot-starter-test`: JUnit 5 + Mockito 등 테스트 라이브러리 포함.
- `JavaLanguageVersion.of(21)`: Java 21을 사용하도록 명시적으로 지정.

### settings.gradle

```groovy
rootProject.name = 'backend'
```

---

## 실습 가이드

### 프로젝트 실행하기

```bash
# 1. 프로젝트 루트로 이동
cd cnu26-backend

# 2. Gradle Wrapper로 실행
./gradlew bootRun
```

실행 후 콘솔에 다음과 같은 로그가 출력되면 성공:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
...
Started BackendApplication in X.XXX seconds
```

> **참고:** 아직 웹 의존성이 없으므로 HTTP 요청을 보낼 수 없다. 애플리케이션은 시작 후 바로 종료된다.

### 확인할 것들

1. Java 21이 설치되어 있는지 확인: `java -version`
2. Gradle Wrapper 실행 권한이 있는지 확인: `chmod +x gradlew` (macOS/Linux)
3. 프로젝트가 정상적으로 빌드되는지 확인: `./gradlew build`

### practice 브랜치

이 단계에는 별도의 practice 브랜치가 없다. `main` 브랜치의 코드를 직접 살펴보며 학습한다.

---

## 핵심 정리

> **Spring Boot는 `@SpringBootApplication` 하나로 자동 설정, 컴포넌트 스캔, 설정 클래스 선언을 모두 처리하며, `build.gradle`의 스타터 의존성이 필요한 라이브러리를 일괄 관리한다.**
