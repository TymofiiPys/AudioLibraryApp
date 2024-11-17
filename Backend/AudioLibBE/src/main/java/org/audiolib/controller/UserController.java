package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.UserDTO;
import org.audiolib.entity.User;
import org.audiolib.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
       int login = service.login(userDTO);
        if(login == -1) {
            return ResponseEntity.notFound().build();
        }
        if(login == -2 || login == -3){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("Successfully authorized");
    }

    @PostMapping("/register")
    private ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        User optUser = service.register(userDTO);
        return ResponseEntity.ok("Successfully registered");
    }
}
