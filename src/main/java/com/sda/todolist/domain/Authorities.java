package com.sda.todolist.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authorities {

    @Id
    private String email;

    private String authority;

    public Authorities(String email, String authority) {
        this.email = email;
        this.authority = authority;
    }

    public Authorities() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
