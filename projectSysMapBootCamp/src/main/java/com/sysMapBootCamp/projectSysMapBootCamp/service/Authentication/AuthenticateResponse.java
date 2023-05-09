package com.sysMapBootCamp.projectSysMapBootCamp.service.Authentication;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthenticateResponse {

    public UUID user_id;
    public String token;
}
