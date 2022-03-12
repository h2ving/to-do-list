package com.sda.todolist.repository;

import com.sda.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);
}
