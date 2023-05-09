package com.sysMapBootCamp.projectSysMapBootCamp.service.followers;

import com.sysMapBootCamp.projectSysMapBootCamp.data.FollowersRepository;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Followers;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FollowerService implements  IFollowerService{

    @Autowired
    private FollowersRepository _followersRepository;

    public void addFollower(FollowerRequest request){

        var newFollower = new Followers(request.followerUserId, request.followedUserId);
        _followersRepository.save(newFollower);
    }

    public List<FollowedsResponse> getFolloweds(){
        var user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var listFolloweds= _followersRepository.findFollowersByFollowerUserId(user.getId());
        return listFolloweds;
    }

    public void removeFollowed(UUID followedUserId){
        var user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        _followersRepository.removeByFollowerUserIdAndFollowedUserId(user.getId(),followedUserId);
    }
}
