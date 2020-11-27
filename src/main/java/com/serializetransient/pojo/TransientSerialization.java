package com.serializetransient.pojo;

/**
 * transient used to hide/not to expose fields in Serialized JSON
 * transient -> default it return null value in deserialization. Hence, we should not use for deserialization
 */
public class TransientSerialization {
    private String name;
    private String lastName;
    private Integer age;
    private transient Boolean isDeveloper;

    public TransientSerialization(String name, String lastName, Integer age, Boolean isDeveloper){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.isDeveloper = isDeveloper;
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

    public Boolean getDeveloper() {
        return isDeveloper;
    }

    public void setDeveloper(Boolean developer) {
        isDeveloper = developer;
    }
}
