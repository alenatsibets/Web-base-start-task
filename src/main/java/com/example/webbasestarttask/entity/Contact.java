package com.example.webbasestarttask.entity;

import java.util.Objects;

public class Contact extends AbstractEntity{
    private String name;
    private String phoneNumber;
    private int userId;

    public Contact(int userId, String phoneNumber, String name) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
    public Contact(int id, int userId, String phoneNumber, String name) {
        super.setId(id);
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return userId == contact.userId && Objects.equals(name, contact.name) && Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, userId);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userId=" + userId +
                '}';
    }
}