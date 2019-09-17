package com.robin.hood.repository;

import com.robin.hood.entity.User;
import reactor.core.publisher.Flux;

public interface UserRepositoryCustom {

    Flux<User> getAllUsersByCriteria(String realName, Integer posts, Integer followers, Integer following);

}
