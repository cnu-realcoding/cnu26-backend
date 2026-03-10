# Step 2: Web 의존성 추가

> **브랜치:** `web/start`

---

## 학습 목표

- `spring-boot-starter-web` 의존성의 역할을 이해한다.
- 내장 톰캣(Embedded Tomcat)이 무엇인지 알고, 왜 사용하는지 설명할 수 있다.
- 의존성 하나 추가로 Spring MVC + 내장 서버가 자동 설정되는 원리를 파악한다.
- `build.gradle` 한 줄 변경 전/후의 차이를 체감한다.

---

## 핵심 개념 설명

### spring-boot-starter-web이 가져오는 것들

`spring-boot-starter-web` 한 줄을 추가하면 다음이 모두 포함된다:

| 포함 라이브러리 | 역할 |
|---|---|
| Spring MVC | 웹 MVC 프레임워크 (컨트롤러, 요청 매핑 등) |
| Embedded Tomcat | 내장 웹 서버 (별도 설치 불필요) |
| Jackson | JSON 직렬화/역직렬화 |
| Bean Validation | 입력값 검증 |

### 내장 톰캣(Embedded Tomcat)이란?

**전통적인 방식:**
1. Tomcat 서버를 별도로 설치한다.
2. 프로젝트를 WAR 파일로 패키징한다.
3. WAR를 Tomcat에 배포(deploy)한다.

**Spring Boot 방식:**
1. 프로젝트 안에 Tomcat이 내장되어 있다.
2. JAR 파일 하나로 패키징한다.
3. `java -jar app.jar` 또는 `./gradlew bootRun`으로 바로 실행한다.

> **핵심:** 서버를 설치하고 배포하는 과정이 사라진다. 개발과 배포가 훨씬 간단해진다.

### Auto Configuration이 하는 일

`spring-boot-starter-web`을 추가하면 Spring Boot의 자동 설정이:

1. **DispatcherServlet**을 등록한다 (요청을 적절한 컨트롤러로 라우팅).
2. **내장 톰캣**을 기본 포트 8080으로 시작한다.
3. **Jackson ObjectMapper**를 등록한다 (객체를 JSON으로 변환).
4. **기본 에러 페이지**를 설정한다 (/error).

---

## 주요 코드

### Before (main 브랜치)

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

실행 결과: 애플리케이션이 시작 후 **바로 종료**된다.

```
Started BackendApplication in 0.8 seconds
// 프로세스 종료
```

### After (web/start 브랜치)

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web'    // 추가!
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

실행 결과: 톰캣이 시작되고 **8080 포트에서 대기**한다.

```
Tomcat initialized with port 8080 (http)
Started BackendApplication in 1.5 seconds
```

### 변경 요약 (diff)

```diff
 dependencies {
     implementation 'org.springframework.boot:spring-boot-starter'
+    implementation 'org.springframework.boot:spring-boot-starter-web'
     testImplementation 'org.springframework.boot:spring-boot-starter-test'
     testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
 }
```

> **단 한 줄의 변경**으로 웹 서버가 내장된 애플리케이션이 된다.

---

## 실습 가이드

### 1. 브랜치 전환

```bash
git checkout web/start
```

### 2. 의존성 확인

```bash
# 프로젝트의 모든 의존성 확인
./gradlew dependencies --configuration runtimeClasspath
```

출력에서 `spring-webmvc`, `tomcat-embed-core`, `jackson-databind` 등이 포함된 것을 확인할 수 있다.

### 3. 실행 및 확인

```bash
# 실행
./gradlew bootRun

# 다른 터미널에서 확인
curl http://localhost:8080
```

아직 컨트롤러가 없으므로 `/error` 페이지(Whitelabel Error Page)가 응답된다. 이것은 정상이다 -- 서버가 동작하고 있다는 증거다.

### 4. 포트 변경해보기 (선택)

`src/main/resources/application.properties`에 다음을 추가:

```properties
server.port=9090
```

다시 실행하면 9090 포트에서 시작되는 것을 확인할 수 있다.

### practice 브랜치

이 단계에는 별도의 practice 브랜치가 없다.

---

## 핵심 정리

> **`spring-boot-starter-web` 한 줄 추가만으로 내장 톰캣 + Spring MVC + Jackson이 자동 설정되어, 별도의 서버 설치 없이 HTTP 요청을 처리할 수 있는 웹 애플리케이션이 된다.**
