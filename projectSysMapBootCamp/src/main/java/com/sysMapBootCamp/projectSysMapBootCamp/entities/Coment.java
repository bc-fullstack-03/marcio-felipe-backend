package com.sysMapBootCamp.projectSysMapBootCamp.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Coment {

    private UUID id;
    private UUID user_id;
    private Date dateCreated;
    private String comentText;
    private List<Like> likes;

    public Coment(UUID user_id, String comentText) {
        this.id = UUID.randomUUID();
        this.user_id = user_id;
        this.comentText = comentText;
        this.dateCreated = new Date();
        this.likes = new ArrayList<>();
    }

    public Coment() {
    }
}
