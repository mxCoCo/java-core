package com.newFeture.lambda;

import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User(Integer id, String name, Integer age, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
    }


    public User(Integer id) {
        this.id = id;
    }

    public User() {
    }

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(age, user.age) &&
                status == user.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, age, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", status=" + status +
                '}';
    }

    public enum Status{
        FREE,BUSY,VACATION;
   }
}
