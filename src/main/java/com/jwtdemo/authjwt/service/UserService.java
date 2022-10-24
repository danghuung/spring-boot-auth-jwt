package com.jwtdemo.authjwt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtdemo.authjwt.entity.CustomUserDetail;
import com.jwtdemo.authjwt.entity.User;
import com.jwtdemo.authjwt.reponsitory.UserReponsitory;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserReponsitory userReponsitory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userReponsitory.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReponsitory.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userReponsitory.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with" + id);
        }

        return new CustomUserDetail(user);
    }
}
