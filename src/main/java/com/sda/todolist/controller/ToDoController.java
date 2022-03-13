package com.sda.todolist.controller;

import com.sda.todolist.domain.User;
import com.sda.todolist.domain.request.ToDoRequest;
import com.sda.todolist.domain.response.ToDoResponse;
import com.sda.todolist.service.ToDoElementService;
import com.sda.todolist.service.UserCache;
import com.sda.todolist.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@CrossOrigin("http://localhost:4200/")
public class ToDoController {

    @Autowired
    private ToDoElementService elementService;

    @Autowired
    UserCache userCache;

    @PostMapping("/")
    public ResponseEntity<List<ToDoResponse>> createToDoElement(@RequestBody ToDoRequest request) {

        User user = userCache.getByUsername(UserUtils.getAuthenticatedUserName());

        return ResponseEntity.ok(elementService.createNewToDoElement(user.getId(), request));
    }

    @GetMapping("/")
    public ResponseEntity<List<ToDoResponse>> getAllElements() {

        User user = userCache.getByUsername(UserUtils.getAuthenticatedUserName());

        return ResponseEntity.ok(elementService.getAll(user.getId()));
    }

    @PostMapping("/{id}/complete")
    public void updateCompletionStatus(@PathVariable int id) {
        elementService.markAsComplete(id);
    }
}
