package com.sysMapBootCamp.projectSysMapBootCamp.service.Authentication;

import lombok.Data;

@Data
public class AuthenticateRequest {
    public String email;
    public String password;
}
