package com.sysMapBootCamp.projectSysMapBootCamp.api;

import com.sysMapBootCamp.projectSysMapBootCamp.service.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    @Autowired
    private IPostService _postService;

    @PostMapping("/createPostWithPhoto")
    public ResponseEntity createPostWithPhoto(@RequestPart MultipartFile photo, @RequestPart String postDescription) {
        try {
            _postService.createPost(postDescription, photo);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/createPost")
    public ResponseEntity createPost(@RequestPart String postDescription) {
        try {
            _postService.createPost(postDescription);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getPostByUser")
    public ResponseEntity<List<PostResponse>> getPostByUser(){
        try {
            var listPost =_postService.getAllPostByUser();
            return  ResponseEntity.ok().body(listPost);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<List<PostResponse>> getPosts(){
        try {
            var listPost =_postService.getAllPost();
            return  ResponseEntity.ok().body(listPost);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deletePost")
    public ResponseEntity deletePost(@RequestParam UUID postId){
        try {
            _postService.removePost(postId);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/addComent")
    public ResponseEntity addComent(@RequestBody ComentCreateRequest request){
        try {
            _postService.addComent(request);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeComent")
    public  ResponseEntity removeComent(@RequestBody ComentRemoveRequest request){
        try {
            _postService.removeComent(request);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addLike")
    public ResponseEntity addLike(@RequestBody LikeCreateRequest request){
        try {
            _postService.addLike(request);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeLike")
    public  ResponseEntity removeLike(@RequestBody LikeRemoveRequest request){
        try {
            _postService.removeLike(request);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addComentLike")
    public ResponseEntity addComent(@RequestBody ComentLikeCreateRequest request){
        try {
            _postService.addComentLike(request);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeComentLike")
    public  ResponseEntity removeComent(@RequestBody ComentLikeRemoveRequest request){
        try {
            _postService.removeComentLike(request);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
