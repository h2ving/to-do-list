package com.sda.todolist.controller;

import com.sda.todolist.domain.User;
import com.sda.todolist.domain.request.ToDoRequest;
import com.sda.todolist.service.ToDoElementService;
import com.sda.todolist.service.UserCache;
import com.sda.todolist.service.UserService;
import com.sda.todolist.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoWebController {

    @Autowired
    private ToDoElementService elementService;

    @Autowired
    private UserCache userCache;


    @GetMapping("/")
    public String showToDoList(ToDoRequest toDoRequest, Model model) throws Exception {

        User user = userCache.getByUsername(UserUtils.getAuthenticatedUserName());

        if(user != null) {
            model.addAttribute("result", elementService.getAll(user.getId()));
            model.addAttribute("userId", user.getId());
        }
        return "todo-home";
    }

    @GetMapping("/add-todo")
    public String addTodoView(ToDoRequest toDoRequest) {
        return "add-todo";
    }

    @PostMapping("/add")
    public String addTodo(ToDoRequest toDoRequest, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return "todo-home";
        }

        User user = userCache.getByUsername(UserUtils.getAuthenticatedUserName());
        if(user != null) {
            elementService.createNewToDoElement(user.getId(), toDoRequest);
        }
        return "redirect:/";
    }

    @PostMapping("/{id}/complete")
    public String markAsComplete(@PathVariable int id, ToDoRequest toDoRequest, BindingResult result, Model model) {
        elementService.markAsComplete(id);
        return "redirect:/";
    }
}
