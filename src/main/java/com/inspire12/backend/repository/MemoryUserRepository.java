package com.inspire12.backend.repository;

import com.inspire12.backend.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// TODO: @Repository 애노테이션이 붙어있는지 확인하세요 (스프링 빈 등록)
@Repository
public class MemoryUserRepository implements UserRepository {

    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public MemoryUserRepository() {
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
        // TODO: store 에서 id 로 조회하여 Optional 로 반환하세요
        // 힌트: Optional.ofNullable(store.get(id))
        return Optional.empty();
    }

    // TODO: findByNameContaining 을 구현하세요
    // 힌트: store.values().stream().filter(...).toList()

    @Override
    public User save(User user) {
        // TODO: user.id() 가 null 이면 idGenerator 로 새 ID 를 생성하세요
        // TODO: new User(id, user.name(), user.email()) 을 store 에 put 하고 반환하세요
        return null;
    }

    // TODO: deleteById 를 구현하세요
    // 힌트: store.remove(id)

    // TODO: existsById 를 구현하세요
    // 힌트: store.containsKey(id)

    @Override
    public long count() {
        return store.size();
    }
}
