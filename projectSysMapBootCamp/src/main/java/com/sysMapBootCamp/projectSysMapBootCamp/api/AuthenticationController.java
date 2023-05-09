package com.sysMapBootCamp.projectSysMapBootCamp.api;


import com.sysMapBootCamp.projectSysMapBootCamp.service.Authentication.AuthenticateResponse;
import com.sysMapBootCamp.projectSysMapBootCamp.service.Authentication.AuthenticateRequest;
import com.sysMapBootCamp.projectSysMapBootCamp.service.Authentication.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

        @Autowired
        private IAuthenticationService _authenticationService;

        @PostMapping("/login")
        public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request){

                try {
                        return ResponseEntity.ok().body(_authenticationService.authenticate(request));

                }catch (Exception e){
                        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
                }
        }
}
