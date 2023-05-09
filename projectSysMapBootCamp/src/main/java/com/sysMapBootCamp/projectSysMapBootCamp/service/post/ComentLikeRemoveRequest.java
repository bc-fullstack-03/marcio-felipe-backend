package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Coment;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Like;
import lombok.Data;

import java.util.UUID;

@Data
public class ComentLikeRemoveRequest {
        public UUID postId;
        public UUID user_id;
        public Coment coment;
        public Like like;

        public void removeLike(){
            coment.getLikes().remove(like);
        }
}
