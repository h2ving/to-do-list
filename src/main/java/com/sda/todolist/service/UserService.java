package com.sda.todolist.service;

import com.sda.todolist.domain.Authorities;
import com.sda.todolist.domain.User;
import com.sda.todolist.domain.request.UserDto;
import com.sda.todolist.domain.request.UserLoginDto;
import com.sda.todolist.repository.AuthoritiesRepository;
import com.sda.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User createNewUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEnabled(true);

        User savedUser = userRepository.save(user);
        authoritiesRepository.save(new Authorities(savedUser.getEmail(), "USER"));
        return savedUser;
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);

    }
}
