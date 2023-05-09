package com.sysMapBootCamp.projectSysMapBootCamp.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Post {

    private UUID id;
    private UUID userId;
    private List<Like> likes;
    private List<Coment> coments;
    private String text;
    private String photoUri;
    private Date dateCreated;

    public Post(UUID userId, String text, String photoUri) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.text = text;
        this.photoUri = photoUri;
        this.coments = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dateCreated = new Date();
    }

    public void addComent( Coment coment){
        this.coments.add(coment);
    }

    public void addLike(Like like){
        this.likes.add(like);
    }
}
