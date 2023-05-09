package com.sysMapBootCamp.projectSysMapBootCamp.service.Authentication;

import com.sysMapBootCamp.projectSysMapBootCamp.service.User.IUserService;
import com.sysMapBootCamp.projectSysMapBootCamp.service.security.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserService _userService;
    @Autowired
    private IJwtService _jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;


    public AuthenticateResponse authenticate(AuthenticateRequest request) throws Exception {

        var user = _userService.getUser(request.email);

        if (user == null) {
            return null;
        }

        if(!_passwordEncoder.matches(request.password,user.getPassword())){
            throw new Exception("Invalid Password");
        }

        var response = new AuthenticateResponse();

        response.setUser_id(user.getId());
        response.setToken(_jwtService.generateToken(user.getId()));

        return response;
    }
}
