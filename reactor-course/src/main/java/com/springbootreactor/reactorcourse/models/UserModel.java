package com.springbootreactor.reactorcourse.models;

public class UserModel {
    private String name;

    public UserModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
