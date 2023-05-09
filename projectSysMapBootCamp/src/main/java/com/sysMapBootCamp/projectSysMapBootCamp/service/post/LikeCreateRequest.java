package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeCreateRequest {
    public UUID postId;
    public UUID userId;
}
