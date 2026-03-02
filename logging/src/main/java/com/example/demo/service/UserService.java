package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    private final UserRepository userRepository;

    public Page<User> findAll(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);

        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new NoResultException("User not found"));

        try {
            MDC.put("userId", user.getName());
            log.info("User found: {}", user.getName());
            log.warn("sfdsfs");
        } catch (Exception e) {
            MDC.remove("userId");
            MDC.clear();
            throw new RuntimeException(e);
        }
        return user;
    }

    @Transactional
    public void save(User user) {
//        userDao.save(user);
        System.out.println(user.getName());
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByNationality(String nationality) {
//        return userRepository.findByPassport_Nationality(nationality);
        return userRepository.findByNationality(nationality);
    }
}
