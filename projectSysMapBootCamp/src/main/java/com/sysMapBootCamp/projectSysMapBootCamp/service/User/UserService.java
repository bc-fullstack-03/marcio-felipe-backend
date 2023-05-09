package com.sysMapBootCamp.projectSysMapBootCamp.service.User;

import com.sysMapBootCamp.projectSysMapBootCamp.data.UserRepository;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.User;
import com.sysMapBootCamp.projectSysMapBootCamp.service.fileUpload.IFileUploadService;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.FollowedsResponse;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.FollowerRequest;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.IFollowerService;
import com.sysMapBootCamp.projectSysMapBootCamp.service.security.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository _userRepositery;

    @Autowired
    private IJwtService _jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private IFileUploadService _fileUploadService;

    @Autowired
    private IFollowerService _followerService;

    public String createUser(CreateUserRequest request) {
        try {
            var newUser = new User(request.name,request.email);

            if(!_userRepositery.findUserByEmail(request.email).isEmpty()){
                return null;
            }

            var hash = _passwordEncoder.encode(request.password);

            newUser.setPassword(hash);

            _userRepositery.save(newUser);

            String response = "Novo usuário com id " + newUser.getId().toString() + " criado com sucesso!";
            return response;

        } catch (Exception e) {
            String response = "Erro ao criar o Usuário.\nErro:" + e.getMessage();
            return response;
        }

    }

    public FindUserResponse findUserByEmail(String email) {

        var user = _userRepositery.findUserByEmail(email).get();


        var response = new FindUserResponse(user.getId(),user.getName(),user.getEmail(),user.getPhotoUri());
        return response;
    }


    public User getUserById(UUID userId) {
        return _userRepositery.findUserById(userId).get();
    }

    public User getUser(String email){
        return _userRepositery.findUserByEmail(email).get();
    }

    public void uploadPhotoProfile (MultipartFile photo) throws Exception {
        var user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var photoUri = "";
        try{
            var filename = user.getId() + "."+photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1);
            photoUri = _fileUploadService.upload(photo,filename);

        }catch (Exception e){

            throw new Exception(e.getMessage());
        }

        user.setPhotoUri(photoUri);
        _userRepositery.save(user);

    }

    public void addingFollowed(FollowerRequest request){
        _followerService.addFollower(request);
    }

    public List<FollowedsResponse> getAllFollowed(){
        return _followerService.getFolloweds();
    }

    public void removeFollowed(UUID followedUserId){
        _followerService.removeFollowed(followedUserId);
    }
}
