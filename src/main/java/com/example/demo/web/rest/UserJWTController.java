package com.example.demo.web.rest;

import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.web.rest.vm.LoginVM;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserServiceImpl userServiceImpl;

    private JwtProvider jwtProvider;

    public UserJWTController(AuthenticationManagerBuilder authenticationManagerBuilder, UserServiceImpl userServiceImpl, JwtProvider jwtProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userServiceImpl = userServiceImpl;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        // todo ver aqui https://dev.to/nilmadhabmondal/let-s-implement-basic-jwt-based-authentication-in-spring-boot-5ec4
        var email = userServiceImpl.findEmailByLogin(loginVM.getUser());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email,
                loginVM.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.createToken(authentication, loginVM.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);

    }

    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
