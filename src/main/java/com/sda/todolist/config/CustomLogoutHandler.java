package com.sda.todolist.config;

import com.sda.todolist.service.UserCache;
import com.sda.todolist.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    UserCache userCache;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authenticatedUserName = UserUtils.getAuthenticatedUserName();
        userCache.evictUser(authenticatedUserName);
    }
}
