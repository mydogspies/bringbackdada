package com.bringbackdada.site.services;

import com.bringbackdada.site.model.User;
import com.bringbackdada.site.model.UserDetailsWrapper;
import com.bringbackdada.site.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user == null) {
            throw new UsernameNotFoundException("User name: " + s + " not found!");
        }
        return new UserDetailsWrapper(user);
    }
}
