package com.sysMapBootCamp.projectSysMapBootCamp.service.User;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.User;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.FollowedsResponse;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.FollowerRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);

    FindUserResponse findUserByEmail(String email);

    User getUserById(UUID userId);

    User getUser(String email);

    void uploadPhotoProfile (MultipartFile photo) throws Exception;
    void addingFollowed(FollowerRequest request);
    List<FollowedsResponse> getAllFollowed();

    void removeFollowed(UUID followedUserId);
}
