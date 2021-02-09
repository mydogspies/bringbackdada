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

    @Autowired
    public AppSecurityConfig(UserService userService) {
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
                        "/gallery/image/thumbs/*",
                        "/blog/image/*",
                        "/project/image/*",
                        "/project/image/thumb/*",
                        "/blog/*",
                        "/bootstrap-4.5.2-dist/js/*",
                        "/bootstrap-4.5.2-dist/css/*",
                        "/friendlycaptcha/*")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}
