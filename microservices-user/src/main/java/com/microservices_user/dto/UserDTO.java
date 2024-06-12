package com.microservices_user.dto;

import com.microservices_user.entities.Role;
import com.microservices_user.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDTO {
    private Long id;
    private String name;
    private Role role;
    private Status status;


    public UserDTO() {
    }

    public UserDTO(Long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.status = Status.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
