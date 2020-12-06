package com.expose.pojo;

import com.google.gson.annotations.Expose;

/**
 *Expose annotation will help to expose properties in DataSerialization and Deserialization
 * Expose annotation will replace setting properties to null values for hiding
 */
public class ExposeUserProperties {

    @Expose()
    private String name;

    @Expose(serialize = true, deserialize = false)
    private String lastName;

    @Expose(serialize = false, deserialize = true)
    private Integer age;

    @Expose(serialize = false, deserialize = false)
    private Boolean isDevelopr;

    public ExposeUserProperties(String name, String lastName, Integer age, Boolean isDevelopr){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.isDevelopr = isDevelopr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDevelopr() {
        return isDevelopr;
    }

    public void setDevelopr(Boolean developr) {
        isDevelopr = developr;
    }
}
