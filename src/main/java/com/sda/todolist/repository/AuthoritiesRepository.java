package com.sda.todolist.repository;

import com.sda.todolist.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
}
