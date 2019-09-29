package com.robin.hood.repository;

import com.robin.hood.entity.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<User, String>, UserRepositoryCustom {

    @AllowFiltering
    Flux<User> findByUserName(String username);

}
