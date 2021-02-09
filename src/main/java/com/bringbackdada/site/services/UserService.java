package com.bringbackdada.site.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails loadUserByUsername(String s);
}
