package com.paravar;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "users", key = "#root.methodName")
    public List<User> getUsers() {
        System.out.println("Fetching all users from DB");
        return userRepository.findAll();
    }
    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        System.out.println("Fetching from DB for id: " + id);
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.of(id));
    }

    @CacheEvict(value = "users", allEntries = true)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        System.out.println("Deleting from db for id: " + id);
        userRepository.deleteById(id);
    }

    // to combine many cache operations in one method
    //@CachePut(value = "users", key = "#user.id")
    @Caching(
            evict = {
                    @CacheEvict(value = "users", allEntries = true)
            },
            put = {
                    @CachePut(value = "users", key = "#user.id")
            }
    )

    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(u -> {
                    System.out.println("Updating user in DB for id: " + id);
                    user.setId(u.getId());
                    userRepository.save(user);
                    return u;
                })
                .orElseThrow(() -> UserNotFoundException.of(id));
    }
}
