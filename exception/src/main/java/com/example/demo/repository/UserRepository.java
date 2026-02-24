package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByPassport_Nationality(String nationality);


    @Query("select u from User u where u.passport.nationality = :nationality")
//    @NativeQuery("Select * from User\n" +
//            "            Join table Passport on (user.passport.id = passport.id) where nationality = \"Bangladeshi\"")
    User findByNationality(String nationality);
}
