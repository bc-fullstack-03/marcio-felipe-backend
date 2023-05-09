package com.sysMapBootCamp.projectSysMapBootCamp.service.post;

import com.sysMapBootCamp.projectSysMapBootCamp.data.PostRepository;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Coment;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Like;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Post;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.User;
import com.sysMapBootCamp.projectSysMapBootCamp.service.fileUpload.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class PostService implements IPostService{

    @Autowired
    private PostRepository _postRepository;

    @Autowired
    private IFileUploadService _fileUploadService;

    public void createPost(String postDescription) throws Exception {
        try {
            var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var photoUri = "";
            var newPost = new Post(user.getId(), postDescription, photoUri);
            if (newPost != null) {
                try {
                    _postRepository.save(newPost);
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void createPost(String postDescription,MultipartFile photo) throws Exception {
        try {
            var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var photoUri = "";

            try{

                var filename = user.getId() + "."+photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1);
                photoUri = _fileUploadService.upload(photo,filename);

                var newPost = new Post(user.getId(), postDescription, photoUri);
                if (newPost != null) {
                    try {
                        _postRepository.save(newPost);
                    } catch (Exception e) {
                        throw new Exception(e.getMessage());
                    }
                }
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<PostResponse> getAllPostByUser() throws Exception {
        List<PostResponse> listPost;
        try {
            var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            listPost = _postRepository.findPostByUserId(user.getId());
            return listPost;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<PostResponse> getAllPost() throws Exception {
        List<PostResponse> listPost;
        try {
            listPost = _postRepository.findAllProjectedBy();
            return listPost;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void removePost(UUID postId) throws Exception {
        try {
            var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            _postRepository.removeByUserIdAndId(user.getId(),postId);
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }

    }


    public void addComent(ComentCreateRequest request) throws Exception {
        try {
            var post = _postRepository.findPostById(request.postId);
            if (post != null){
                var newComent = new Coment(request.userId,request.coment);
                post.get().addComent(newComent);
                _postRepository.save(post.get());
            }else {
                throw  new Exception("Post não encontrado!");
            }
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public void removeComent(ComentRemoveRequest request) throws Exception {
        try {
            var post = _postRepository.findPostById(request.postId);
            if (post != null){
                var comenst = post.get().getComents();
                comenst.remove(request.coment);
                post.get().setComents(comenst);
                _postRepository.save(post.get());
            }else {
                throw  new Exception("Post não encontrado!");
            }
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public void addLike(LikeCreateRequest request) throws Exception {
        try {
            var post = _postRepository.findPostById(request.postId);
            if (post != null){
                var newLike = new Like(request.userId);
                post.get().addLike(newLike);
                _postRepository.save(post.get());
            }else {
                throw  new Exception("Post não encontrado!");
            }
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }


    public void addComentLike(ComentLikeCreateRequest request) throws Exception {
        try {
            var post = _postRepository.findPostById(request.postId);
            if (post != null){
                var coments = post.get().getComents();
                coments.remove(request.coment);
                request.addLike();
                coments.add(request.coment);
                post.get().setComents(coments);
                _postRepository.save(post.get());
            }else {
                throw  new Exception("Post não encontrado!");
            }
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }


    public void removeComentLike(ComentLikeRemoveRequest request) throws Exception {
        try {
            var post = _postRepository.findPostById(request.postId);
            if (post != null){
                var coments = post.get().getComents();
                coments.remove(request.coment);
                request.removeLike();
                coments.add(request.coment);
                post.get().setComents(coments);
                _postRepository.save(post.get());
            }else {
                throw  new Exception("Post não encontrado!");
            }
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public void removeLike(LikeRemoveRequest request) throws Exception {
        try {
            var post = _postRepository.findPostById(request.postId);
            if (post != null){
                var likes = post.get().getLikes();
                likes.remove(request.like);
                post.get().setLikes(likes);
                _postRepository.save(post.get());
            }else {
                throw  new Exception("Post não encontrado!");
            }
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
