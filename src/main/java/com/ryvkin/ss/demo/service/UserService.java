package com.ryvkin.ss.demo.service;

import com.ryvkin.ss.demo.domain.entity.UserEntity;
import com.ryvkin.ss.demo.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        repository.save(userEntity);
        return "User with username " + userEntity.getName() + " and ID " + userEntity.getId() + " has been added to the system.";
    }
}
