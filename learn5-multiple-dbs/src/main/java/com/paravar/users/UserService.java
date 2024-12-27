package com.paravar.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void saveUser() {
        User user = User.builder().name("John").email("user@email.com").age(30).build();
        userRepository.save(user);
    }
}
