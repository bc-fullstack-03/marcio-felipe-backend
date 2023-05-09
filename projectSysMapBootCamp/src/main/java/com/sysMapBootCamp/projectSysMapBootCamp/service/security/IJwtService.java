package com.sysMapBootCamp.projectSysMapBootCamp.service.security;

import java.util.UUID;

public interface IJwtService {

    String generateToken(UUID user_id);
    boolean isValid(String token, String user_id);
}
