package com.example.webbasestarttask.model.entity;

import java.util.Objects;

public class User extends AbstractEntity {
    private String userName;
    private String email;
    private String password;
    private boolean isVerified;


    public User(String userName, String email, String password, boolean isVerified) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
    }

    public User(int id, String userName, String email, String password, boolean isVerified) {
        super.setId(id);
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isVerified == user.isVerified && Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, password, isVerified);
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}