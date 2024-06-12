package com.microservices_user.entities;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(name="users")
public class User {
    @Id
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="user_name")
    private String username;
    private String password;

    public User() {
    }

    public User(Long id, String name, Role role) {
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
        //Ya que estamos generando por defecto active, y quiero actualizar el usuario, me lo cambia a null
        if (status == null) {
            this.status = Status.ACTIVE; // establece el estado a "activo" por defecto
        } else {
            this.status = status;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
