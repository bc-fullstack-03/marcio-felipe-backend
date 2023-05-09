package com.sysMapBootCamp.projectSysMapBootCamp.service.User;

import lombok.Data;

@Data
public class CreateUserRequest {

    public String name;
    public String email;
    public String password;
}
