package com.sda.todolist.repository;

import com.sda.todolist.domain.ToDoElement;
import com.sda.todolist.domain.projection.UserToDoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserToDoRepository extends JpaRepository<ToDoElement, Integer> {

    @Query(value = "SELECT p from ToDoElement p join User u on p.userId=u.id where u.id = :userId")
    List<UserToDoProjection> findAllActiveByUserId(@Param("userId") Integer userId);
}
