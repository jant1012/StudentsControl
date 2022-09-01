package com.janchondo.students.security.controller;

import com.janchondo.students.config.SecurityConfiguration;
import com.janchondo.students.security.entity.AuthenticationRequest;
import com.janchondo.students.security.entity.AuthenticationResponse;
import com.janchondo.students.security.entity.UserModel;
import com.janchondo.students.security.repository.UserRepository;
import com.janchondo.students.security.service.UserService;
import com.janchondo.students.security.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtils jwtUtils;
    private SecurityConfiguration securityConfiguration;

    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils, SecurityConfiguration securityConfiguration) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.securityConfiguration = securityConfiguration;
    }

    @PostMapping("/subs")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest){
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(securityConfiguration.passwordEncoder().encode(password));
        try{
            userRepository.save(userModel);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error during client Subscription " + username));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Successful Subscription for client " + username));
    }

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest){
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error during client Authentication " + username ));
        }

        UserDetails loadedUser = userService.loadUserByUsername(username);
        String generatedToken = jwtUtils.generateToken(loadedUser);
        return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
    }
}
