package com.bringbackdada.site.security;

import com.bringbackdada.site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public AppSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/",
                        "/home",
                        "/css/*",
                        "/js/*",
                        "/images/*",
                        "/site/*",
                        "/fonts/*",
                        "/gallery/image/*",
                        "/blog/image/*",
                        "/project/image/*",
                        "/blog/*")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    // TODO code below obsolete in case of a single user
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = userService.loadUserByUsername("admin");
//        return new InMemoryUserDetailsManager(user);
//    }
}
