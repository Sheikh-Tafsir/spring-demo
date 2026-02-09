package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findUser(Long id) {
        if (id >3) {
            throw new RuntimeException("User not found");
        }
        return new User(id, "Ostad", "ostad@gmail.com");
    }

    public void saveUser(User user) {
        if (user.getId() < 3) {
            throw new RuntimeException("User already exists, cannot save");
        }
        // user saved;
    }

    public void updateUser(Long id, User user) {
        // user updated;
    }

    public void deleteUser(Long id) {
        // user deleted
    }
}
