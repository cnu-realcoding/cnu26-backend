-- 초기 데이터 삽입
-- 기존 MemoryUserRepository 생성자에서 넣던 데이터를 SQL 로 대체
--
-- spring.sql.init.mode=always 설정 시 매번 실행됨
-- INSERT OR IGNORE: 이미 데이터가 있으면 무시 (SQLite 문법)
INSERT OR IGNORE INTO users (id, name, email) VALUES (1, '홍길동', 'hong@example.com');
INSERT OR IGNORE INTO users (id, name, email) VALUES (2, '김철수', 'kim@example.com');
INSERT OR IGNORE INTO users (id, name, email) VALUES (3, '이영희', 'lee@example.com');
