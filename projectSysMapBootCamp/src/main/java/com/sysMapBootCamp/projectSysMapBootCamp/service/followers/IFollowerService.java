package com.sysMapBootCamp.projectSysMapBootCamp.service.followers;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Followers;

import java.util.List;
import java.util.UUID;

public interface IFollowerService {
    void addFollower(FollowerRequest request);
    List<FollowedsResponse> getFolloweds();
    void removeFollowed(UUID followedUserId);
}
