package com.sda.todolist.service;

import com.sda.todolist.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserCache {

    @Autowired
    private UserService userService;

    private final ConcurrentHashMap<String, User> store = new ConcurrentHashMap<>(256);

    public User getByUsername(String username) {
        return store.computeIfAbsent(username, k -> userService.getUserByEmail(username));
    }

    public void evictUser(String username) {
        store.remove(username);
    }
}
