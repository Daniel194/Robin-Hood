package com.robin.hood.entity;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String realName;
    private String profileLink;
    private Integer posts;
    private Integer followers;
    private Integer following;
    private String description;
}
