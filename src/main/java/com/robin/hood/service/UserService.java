package com.robin.hood.service;

import com.robin.hood.entity.User;
import com.robin.hood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        Mono<User> userMono = repository.save(user);

        userMono.subscribe(); // TODO ???
    }

}
