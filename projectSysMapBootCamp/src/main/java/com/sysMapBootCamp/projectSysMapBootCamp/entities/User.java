package com.sysMapBootCamp.projectSysMapBootCamp.entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class User {

    private UUID id;
    private String email;
    private String password;
    private String name;
    private String photoUri;
    private Date dateCreated;

    public User(String name,String email) {
        this.name = name;
        this.email = email;
        this.id = UUID.randomUUID();
        this.dateCreated = new Date();
    }

    public UUID getId() {
        return id;
    }


}
