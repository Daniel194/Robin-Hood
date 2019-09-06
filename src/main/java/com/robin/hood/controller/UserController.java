package com.robin.hood.controller;

import com.robin.hood.entity.User;
import com.robin.hood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    private Mono<User> getUserById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @GetMapping
    private Flux<User> getAllUsers() {
        return repository.findAll();
    }

}
