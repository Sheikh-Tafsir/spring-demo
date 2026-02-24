package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.HasibulNotFound;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final UserRepository userRepository;

    public Page<User> findAll(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);

        Page<User> users = userRepository.findAll(pageable);
        return users;
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        List<User> users = userRepository.findAll(sort);
//        return users;
    }

    public User findById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new NoResultException("User not found"));
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
