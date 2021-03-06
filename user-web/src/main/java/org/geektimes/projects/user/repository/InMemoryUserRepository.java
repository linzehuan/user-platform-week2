package org.geektimes.projects.user.repository;

import org.geektimes.projects.user.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 内存型 {@link UserRepository} 实现
 *
 * @since 1.0s
 */
public class InMemoryUserRepository implements UserRepository {

    private Map<Long, User> repository = new ConcurrentHashMap<>();

    @Override
    public boolean save(User user) {
        return repository.put(user.getId(), user) == null;
    }

    @Override
    public boolean deleteById(Long userId) {
        return repository.remove(userId) != null;
    }

    @Override
    public boolean update(User user) {
        save(user);
        return true;
    }

    @Override
    public User getById(Long userId) {
        return repository.get(userId);
    }

    @Override
    public User getByNameAndPassword(String userName, String password) {
        return repository.values()
                .stream()
                .filter(user -> Objects.equals(userName, user.getName())
                        && Objects.equals(password, user.getPassword()))
                .findFirst()
                .get();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(repository.values());
    }
}
