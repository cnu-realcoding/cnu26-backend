# CNU26 Backend - Real Coding 2026

Spring Boot 기반 백엔드 강의 프로젝트입니다.
쇼핑몰(유저 기능 + 네이버 쇼핑 정보)을 단계별로 만들어갑니다.

## 기술 스택

- Java 21
- Spring Boot 4.0.2
- Gradle

## 실행 방법

```bash
./gradlew bootRun
```

서버 실행 후 http://localhost:8080 으로 접속

## 중간 목표: 쇼핑몰

- 유저 기능 (회원가입, 로그인, 조회)
- 네이버 쇼핑 API 연동 (상품 검색, 상품 정보 조회)

## Step 진행 과정

### Step 1. 프로젝트 초기 설정
- **branch:** `main`
- Spring Boot 프로젝트 생성 및 Gradle 설정

### Step 2. Web 의존성 추가
- **branch:** `web/start`
- `spring-boot-starter-web` 의존성 추가

### Step 3. GET 요청/응답 다루기
- **branch:** `web/get`
- `UserController` - REST API JSON 응답
  - `GET /users/hello` : 단순 문자열 응답
  - `GET /users` : 유저 목록 조회
  - `GET /users/{id}` : PathVariable
  - `GET /users/search?name=` : RequestParam
  - `GET /users/page?page=0&size=10` : RequestParam 기본값 + 페이징
  - `GET /users/me` : RequestHeader (Authorization)
  - `GET /users/{id}/detail` : ResponseEntity 상태코드 제어
- `UserPageController` - HTML 응답 비교
  - `GET /pages/users` : HTML / JSON content negotiation
  - `GET /pages/users/{id}` : HTML 상세 페이지

### Step 4. POST, PUT, DELETE 요청 다루기
- **branch:** `web/post`
- 인메모리 유저 저장소로 CRUD 동작 확인
- `UserController` 확장
  - `POST /users` : RequestBody 로 유저 생성 (201 Created)
  - `PUT /users/{id}` : 유저 정보 전체 수정
  - `DELETE /users/{id}` : 유저 삭제 (204 No Content)

### Step 5. Swagger (OpenAPI) 문서화
- **branch:** `web/swagger`
- `springdoc-openapi-starter-webmvc-ui` 의존성 추가
- Swagger 애노테이션 적용
  - `@Tag` : 컨트롤러 그룹 이름
  - `@Operation` : API 설명 (summary, description)
  - `@Parameter` : 파라미터 설명
  - `@ApiResponse` / `@ApiResponses` : 응답 상태코드 설명
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

### Step 6. 예외 처리와 Global Exception Handler
- **branch:** `web/exception`
- 커스텀 예외 클래스
  - `UserNotFoundException` : 유저 조회 실패 시 (→ 404)
  - `InvalidRequestException` : 잘못된 요청 시 (→ 400)
- `ErrorResponse` record : 통일된 에러 응답 형식 (status, error, message, timestamp)
- `GlobalExceptionHandler` (`@RestControllerAdvice`)
  - `@ExceptionHandler` 로 예외 타입별 응답 분기
  - 컨트롤러에서 `null` 반환 / `ResponseEntity` 분기 → 예외 던지기로 변경
- Before vs After 비교
  - Before: `return ResponseEntity.notFound().build()`
  - After: `throw new UserNotFoundException(id)` → GlobalExceptionHandler 가 처리

### Step 7. 데이터베이스 연동 (예정)
- **branch:** `db/start`
- H2 / JPA 설정, Entity, Repository

### Step 8. 유저 기능 완성 (예정)
- **branch:** `feature/user`
- 회원가입, 로그인, Service 계층 분리

### Step 9. 외부 API 연동 (예정)
- **branch:** `feature/naver-shopping`
- 네이버 쇼핑 API 연동, RestClient 사용

### Step 10. 통합 (예정)
- **branch:** `feature/shopping-mall`
- 유저 기능 + 쇼핑 기능 통합
