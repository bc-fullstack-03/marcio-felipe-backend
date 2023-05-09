package com.sysMapBootCamp.projectSysMapBootCamp.data;

import com.sysMapBootCamp.projectSysMapBootCamp.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(UUID userId);

}
