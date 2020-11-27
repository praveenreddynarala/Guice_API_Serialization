package com.serializednames.pojo;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName(value = "firstName", alternate = "name")
    private String name;
    @SerializedName(value = "surname", alternate = "last_name")
    private String last_name;
    private Integer age;
    private Boolean isDeveloper;

    public Users(String name, String last_name, Integer age, Boolean isDeveloper){
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.isDeveloper = isDeveloper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDeveloper() {
        return isDeveloper;
    }

    public void setDeveloper(Boolean developer) {
        isDeveloper = developer;
    }
}
