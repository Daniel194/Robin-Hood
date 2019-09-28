package com.robin.hood.repository;

import com.robin.hood.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import static org.springframework.data.cassandra.core.query.Criteria.where;
import static org.springframework.data.cassandra.core.query.Query.query;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private ReactiveCassandraTemplate template;

    @Autowired
    public UserRepositoryCustomImpl(ReactiveCassandraTemplate template) {
        this.template = template;
    }

    @Override
    @AllowFiltering
    public Flux<User> getAllUsersByCriteria(String realName, Integer posts, Integer followers, Integer following) {
        realName = threatRealName(realName);

        Query query = query(where("posts").gt(posts))
                .and(where("followers").gt(followers))
                .and(where("following").gt(following))
                .and(where("realName").like(realName));

        return template.select(query.withAllowFiltering(), User.class);
    }

    private String threatRealName(String realName) {
        if (realName.isEmpty() || realName.isBlank()) {
            return realName;
        } else {
            return "%" + realName + "%";
        }
    }

}
