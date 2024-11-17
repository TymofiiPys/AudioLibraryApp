package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.UserDTO;
import org.audiolib.entity.User;
import org.audiolib.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository uRepo;

    public int login(UserDTO userDTO) {
        Optional<User> user = uRepo.findByEmail(userDTO.email());
        if (user.isEmpty())
            return -1;
        else if (user.get().getPassword() != userDTO.password())
            return -2;
        else if (user.get().isBlocked())
            return -3;
        else
            return 0;
    }

    public User register(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.email());
        user.setName(userDTO.email());
        user.setBlocked(false);
        user.setPassword(userDTO.password());
        return uRepo.save(user);
    }
}
