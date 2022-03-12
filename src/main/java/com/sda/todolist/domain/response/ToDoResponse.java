package com.sda.todolist.domain.response;

public class ToDoResponse {
    private int id;
    private String description;
    private boolean isCompleted;
    private int userId;

    public ToDoResponse(int id, String description, boolean isCompleted, int userId) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
