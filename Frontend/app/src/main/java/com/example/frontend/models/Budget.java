package com.example.frontend.models;

public abstract class Budget {
    User user;

    public Budget(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
