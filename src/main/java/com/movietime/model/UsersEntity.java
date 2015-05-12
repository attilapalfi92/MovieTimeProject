package com.movietime.model;

import javax.persistence.*;

/**
 * Created by Attila on 2015-05-06.
 */
@Entity
@Table(name = "users", schema = "", catalog = "movietime2")
public class UsersEntity {
    private int user_id;
    private String userName;
    private String password;
    private String email;
    private String role;

    @Id
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, insertable = true, updatable = true, length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 128)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "role", nullable = true, insertable = true, updatable = true, length = 2,
            columnDefinition = "enum('A','U')")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (user_id != that.user_id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
