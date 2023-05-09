package com.sysMapBootCamp.projectSysMapBootCamp.data;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.Coment;
import com.sysMapBootCamp.projectSysMapBootCamp.entities.Post;
import com.sysMapBootCamp.projectSysMapBootCamp.service.post.PostResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends MongoRepository<Post, UUID> {
    List<PostResponse> findPostByUserId(UUID userId);
    Optional<Post> findPostById(UUID uuid);

    @Query(value = "{}", fields ="{ 'id': 1, 'userId': 1, 'likes': 1, 'coments': 1, 'text': 1, 'photoUri': 1, 'dateCreated': 1}")
    List<PostResponse> findAllProjectedBy();
    void removeByUserIdAndId(UUID UserId,UUID Id);
}
