package com.robin.hood.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table
public class User {
    @PrimaryKey
    private int id;

    private String userName;
    private String realName;
    private String profileLink;
    private Integer posts;
    private Integer followers;
    private Integer following;
    private String description;
}
