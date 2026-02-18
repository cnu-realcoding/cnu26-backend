package com.inspire12.backend.repository;

import com.inspire12.backend.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// 인메모리 구현체: Map 으로 데이터 저장
// 향후 JPA 구현체로 교체 예정
@Repository
public class MemoryUserRepository implements UserRepository {

    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public MemoryUserRepository() {
        // 초기 데이터
        save(new User(null, "홍길동", "hong@example.com"));
        save(new User(null, "김철수", "kim@example.com"));
        save(new User(null, "이영희", "lee@example.com"));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> findByNameContaining(String name) {
        return store.values().stream()
                .filter(u -> u.name().contains(name))
                .toList();
    }

    @Override
    public User save(User user) {
        Long id = user.id();
        if (id == null) {
            id = idGenerator.getAndIncrement();
        }
        User saved = new User(id, user.name(), user.email());
        store.put(id, saved);
        return saved;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    @Override
    public long count() {
        return store.size();
    }
}
