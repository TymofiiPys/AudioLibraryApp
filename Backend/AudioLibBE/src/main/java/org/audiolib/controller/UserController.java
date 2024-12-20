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
    private ResponseEntity<User> login(@RequestBody UserDTO userDTO) {
        Optional<User> user = service.login(userDTO);
        if (user.isEmpty())
            return ResponseEntity.notFound().build();
        else if (!user.get().getPassword().equals(userDTO.password()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        else if (user.get().isBlocked())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        else
            return ResponseEntity.ok(user.get());
    }

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User optUser = service.register(userDTO);
        return ResponseEntity.ok(optUser);
    }
}
