package com.sysMapBootCamp.projectSysMapBootCamp.service.followers;

import lombok.Data;

import java.util.UUID;

@Data
public class FollowedsResponse {
    public UUID followerUserId;
    public UUID followedUserId;
}
