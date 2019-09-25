package com.robin.hood.controller;

import com.robin.hood.entity.User;
import com.robin.hood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000"})
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

    @GetMapping("/criteria")
    private Flux<User> getAllUsersByCriteria(@RequestParam(defaultValue = "") String realName,
                                             @RequestParam(defaultValue = "0") Integer posts,
                                             @RequestParam(defaultValue = "0") Integer followers,
                                             @RequestParam(defaultValue = "0") Integer following) {

        return repository.getAllUsersByCriteria(realName, posts, followers, following);
    }

}
