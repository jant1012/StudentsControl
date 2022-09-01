package com.janchondo.students.security.service;

import com.janchondo.students.security.entity.UserModel;
import com.janchondo.students.security.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel foundedUser = userRepository.findByUsername(username);
        if(foundedUser == null)
            return null;
        String name = foundedUser.getUsername();
        String pwd = foundedUser.getPassword();

        return new User(name, pwd, new ArrayList<>());
    }
}
