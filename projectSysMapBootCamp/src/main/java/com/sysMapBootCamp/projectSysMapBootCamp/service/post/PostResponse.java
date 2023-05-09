package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Coment;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Like;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PostResponse {

    public UUID id;
    private UUID userId;
    public List<Like> likes;
    public List<Coment> coments;
    public String text;
    public String photoUri;
    public Date dateCreated;
}
