package com.tottidev.LotoUrban.controller;

import com.tottidev.LotoUrban.domain.user.User;
import com.tottidev.LotoUrban.domain.user.UserDataLogin;
import com.tottidev.LotoUrban.infra.security.DataJWTToken;
import com.tottidev.LotoUrban.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UserDataLogin userDataLogin) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(userDataLogin.username(), userDataLogin.password());

        try {
            var userAuth = authenticationManager.authenticate(authToken);
            var tokenJWT = tokenService.genToken((User) userAuth.getPrincipal());

            return ResponseEntity.ok(new DataJWTToken(tokenJWT));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
