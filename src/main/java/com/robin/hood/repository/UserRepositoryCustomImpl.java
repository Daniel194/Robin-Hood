package com.robin.hood.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.robin.hood.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import static com.datastax.driver.core.querybuilder.QueryBuilder.select;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private ReactiveCassandraTemplate template;

    @Autowired
    public UserRepositoryCustomImpl(ReactiveCassandraTemplate template) {
        this.template = template;
    }

    @Override
    public Flux<User> getAllUsersByCriteria(String realName, Integer posts, Integer followers, Integer following) {
        posts = posts == null ? 0 : posts;
        followers = followers == null ? 0 : followers;
        following = following == null ? 0 : following;

        Select.Where where = select().from("user").where(QueryBuilder.gt("posts", posts))
                .and(QueryBuilder.gt("followers", followers))
                .and(QueryBuilder.gt("following", following));


        if (realName != null) {
            where.and(QueryBuilder.like("realName", realName));
        }

        return template.select(where, User.class);
    }
}
