package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Like;
import lombok.Data;

import java.util.UUID;

@Data
public class LikeRemoveRequest {

    public UUID postId;
    public Like like;
}
