package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Coment;
import lombok.Data;

import java.util.UUID;

@Data
public class ComentRemoveRequest {
    public UUID postId;
    public Coment coment;
}
