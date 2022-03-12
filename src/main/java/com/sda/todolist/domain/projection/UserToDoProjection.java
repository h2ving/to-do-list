package com.sda.todolist.domain.projection;

public interface UserToDoProjection {
    int getId();
    String getDescription();
    boolean isCompleted();
    int getUserId();
    String getName();
}