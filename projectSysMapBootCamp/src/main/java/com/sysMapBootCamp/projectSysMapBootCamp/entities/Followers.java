package com.sysMapBootCamp.projectSysMapBootCamp.entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Followers {

    private UUID id;
    private UUID followerUserId;
    private UUID followedUserId;
    private Date dateCreated;

    public Followers() {
    }

    public Followers(UUID followerUserId, UUID followedUserId) {
        this.id=UUID.randomUUID();
        this.followerUserId = followerUserId;
        this.followedUserId = followedUserId;
        this.dateCreated = new Date();
    }
}
