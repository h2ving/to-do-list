package com.sda.todolist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDoElement {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String description;

    @Column
    private boolean isCompleted;

    @Column
    private int userId;

    public ToDoElement() {
    }

    public ToDoElement(String description, boolean isCompleted, int userId) {
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
