package com.sda.todolist.controller;

import com.sda.todolist.domain.request.ToDoRequest;
import com.sda.todolist.domain.response.ToDoResponse;
import com.sda.todolist.service.ToDoElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    @Autowired
    private ToDoElementService elementService;

    @PostMapping("/{userId}")
    public ResponseEntity<List<ToDoResponse>> createToDoElement(@PathVariable int userId, @RequestBody ToDoRequest request) {
        return ResponseEntity.ok(elementService.createNewToDoElement(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ToDoResponse>> getAllElements(@PathVariable int userId) {
        return ResponseEntity.ok(elementService.getAll(userId));
    }

    @PostMapping("/{id}/complete")
    public void updateCompletionStatus(@PathVariable int id) {
        elementService.markAsComplete(id);
    }
}
