package com.ryvkin.ss.demo.config;

import com.ryvkin.ss.demo.domain.entity.UserEntity;
import com.ryvkin.ss.demo.repository.UserEntityRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEntityUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = {@Autowired})
    private UserEntityRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = repository.findByName(username);
        return userEntity.map(UserEntityToUserDetailsConverter::new)
            .orElseThrow(() -> new UsernameNotFoundException("Username is not found" + username));
    }
}
