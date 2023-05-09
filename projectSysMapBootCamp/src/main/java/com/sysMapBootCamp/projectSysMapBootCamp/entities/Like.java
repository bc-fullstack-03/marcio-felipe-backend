package com.sysMapBootCamp.projectSysMapBootCamp.entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Like {
    private UUID id;
    private UUID user_id;
    private Date dateCreated;

    public Like(UUID user_id) {
        this.user_id = user_id;
        this.id=UUID.randomUUID();
        this.dateCreated = new Date();
    }

    public Like() {
    }
}
