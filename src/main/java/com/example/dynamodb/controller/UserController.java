package com.example.dynamodb.controller;

import com.example.dynamodb.model.User;
import com.example.dynamodb.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") String userid) {
        return userRepo.getUserById(userid);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") String userid) {
        return  userRepo.delete(userid);
    }

    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable("id") String userid, @RequestBody User user) {
        return userRepo.update(userid,user);
    }
}
