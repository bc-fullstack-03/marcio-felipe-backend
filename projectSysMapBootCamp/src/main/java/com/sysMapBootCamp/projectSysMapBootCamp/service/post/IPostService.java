package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    void createPost(String postDescription) throws Exception;
    void createPost(String postDescription, MultipartFile photo) throws Exception;
    List<PostResponse> getAllPostByUser() throws Exception;
     List<PostResponse> getAllPost() throws Exception;

    void removePost(UUID postId) throws Exception;

    void addComent(ComentCreateRequest request) throws Exception;

    void removeComent(ComentRemoveRequest request) throws Exception;
    void removeLike(LikeRemoveRequest request) throws Exception;
    void addLike(LikeCreateRequest request) throws Exception;

    void addComentLike(ComentLikeCreateRequest request) throws Exception;

    void removeComentLike(ComentLikeRemoveRequest request) throws Exception;
}
