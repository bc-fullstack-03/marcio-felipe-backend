package com.sysMapBootCamp.projectSysMapBootCamp.api;

import com.sysMapBootCamp.projectSysMapBootCamp.service.User.FindUserResponse;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.FollowedsResponse;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.FollowerRequest;
import com.sysMapBootCamp.projectSysMapBootCamp.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sysMapBootCamp.projectSysMapBootCamp.service.User.CreateUserRequest;
import com.sysMapBootCamp.projectSysMapBootCamp.service.User.IUserService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private IUserService _userService;


    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request){
        //OBS: Validações são feitas no Controler(Recomendavel criação de classes validadoras
        var response = _userService.createUser(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getUser")
    public ResponseEntity<FindUserResponse> getUser(@RequestParam String email){
        return ResponseEntity.ok().body(_userService.findUserByEmail(email));
    }

    @PostMapping("/uploadPhotoProfile")
    public ResponseEntity uploadPhotoProfile(@RequestParam("photo") MultipartFile photo){
        try{

            _userService.uploadPhotoProfile(photo);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addFollower")
    public ResponseEntity addFollower(@RequestBody FollowerRequest request){
        try{
            _userService.addingFollowed(request);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getFolloweds")
    public ResponseEntity<List<FollowedsResponse>> getAllFolloweds (){
            var listFolloweds =_userService.getAllFollowed();
            return ResponseEntity.ok().body(listFolloweds);
    }

    @PostMapping("/deleteFollowed")
    public ResponseEntity deleteFollowed(@RequestParam UUID followedUserId){
        try {
            _userService.removeFollowed(followedUserId);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

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
    public ResponseEntity createPost(@RequestBody String postDescription) {
        try {
            _postService.createPost(postDescription);
            return  new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
