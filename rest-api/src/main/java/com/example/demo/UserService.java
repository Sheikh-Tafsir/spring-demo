package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(Long id) {
        User user = userRepository.findByName("tafsir");
        User user = userRepository.findByEmail("tafsir@gmail.com");
        User user = userRepository.findById(id).get();
        if (user == null) throw new RuntimeException("User not found");
        return user;

//        List<User> users = userRepository.findAll();
//        System.out.println(users.get(0).getName());
//        return users.get(0);
    }

    public void saveUser(User user) {
        userRepository.save(user);

    }

    public void updateUser(Long id, User user) {
        User user1 = userRepository.findById(id);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
