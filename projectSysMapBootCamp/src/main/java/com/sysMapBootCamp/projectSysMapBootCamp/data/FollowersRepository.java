package com.sysMapBootCamp.projectSysMapBootCamp.data;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Followers;
import com.sysMapBootCamp.projectSysMapBootCamp.service.followers.FollowedsResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FollowersRepository extends MongoRepository<Followers, UUID> {

    List<FollowedsResponse> findFollowersByFollowerUserId(UUID followerUserId);

    void removeByFollowerUserIdAndFollowedUserId(UUID followerUserId,UUID followedUserId);
}
