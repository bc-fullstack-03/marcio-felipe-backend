package com.sysMapBootCamp.projectSysMapBootCamp.service.Authentication;

public interface IAuthenticationService {
    AuthenticateResponse authenticate(AuthenticateRequest request) throws Exception;
}
