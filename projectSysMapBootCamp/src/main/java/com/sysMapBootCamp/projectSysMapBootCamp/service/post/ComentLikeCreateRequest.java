package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Coment;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Like;
import lombok.Data;

import java.util.UUID;

@Data
public class ComentLikeCreateRequest {
    public UUID postId;
    public UUID user_id;
    public Coment coment;

    public void addLike() {
        coment.getLikes().add(new Like(user_id));
    }
}
