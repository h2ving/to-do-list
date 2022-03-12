package com.sda.todolist.controller;

import com.sda.todolist.domain.User;
import com.sda.todolist.domain.request.UserDto;
import com.sda.todolist.domain.request.UserLoginDto;
import com.sda.todolist.service.UserCache;
import com.sda.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/registration")
    public String userRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "user-registration";
    }

    @GetMapping("/user/login")
    public String userLoginForm(WebRequest request, Model model) {
        UserLoginDto userDto = new UserLoginDto();
        model.addAttribute("user", userDto);
        return "user-login";
    }

    @GetMapping("/user/logout")
    public String userLogoutForm() {
        return "redirect:/user/login";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-registration";
        }

        User user = userService.createNewUser(userDto);
        return "redirect:/";
    }
/**
    @PostMapping("/user/login")
    public String loginUserAccount(@ModelAttribute("user") @Valid UserLoginDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-login";
        }

        User user = null;
        try {
            user = userService.getUserByEmail(userDto);
        } catch (Exception e) {
            ObjectError error = new ObjectError("error", "Incorrect Password");
            result.addError(error);
            return "user-login";
        }

        return "redirect:/" + user.getId();
    }
    */
}
