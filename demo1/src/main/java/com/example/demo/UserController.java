package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// localhost:8080/users?gender=female&age=25&address=dhaka
// localhost:8080/users/35
//localhost:8080/product/3

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        User user = userService.findUser(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String gender,
                                   @RequestParam(required = false) Integer age) {
       List<User> userList = new ArrayList<>();
       userList.add(new User(1L, "Ostad1", "ostad1@gmail.com"));
       userList.add(new User(2L, "Ostad2", "ostad2@gmail.com"));
       userList.add(new User(3L, "Ostad3", "ostad3@gmail.com"));

       return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(userList);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveUser(user);
        String message = "user saved";
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        String message = "user updated";
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        String message = "user deleted";
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(message);
    }
}
