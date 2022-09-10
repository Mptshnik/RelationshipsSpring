package com.dbexample.demo.model;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;


@Entity
public class User
{
    public User()
    {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public User(String firstName, String lastname, String middleName, int age, int height, int weight) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.middleName = middleName;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    @NotNull
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min=2, max=30, message = "Имя должно находится в диапазоне от 2 до 30 символов")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min=2, max=30, message = "Фамилия должна находится в диапазоне от 2 до 30 символов")
    private String lastname;

    @NotNull
    @NotEmpty(message = "Отчество не может быть пустым")
    @Size(min=2, max=30, message = "Отчество должно находится в диапазоне от 2 до 30 символов")
    private String middleName;

    @NotNull(message = "Не пустое")
    @Min(value = 18, message = "Минимальный возраст 18 лет")
    @Max(value = 99, message = "Максимальный возраст 99 лет")
    private int age;

    @NotNull(message = "Не пустое")
    @Min(value = 50, message = "Минимальный рост 50")
    @Max(value = 300, message = "Максимальный рост 300")
    private int height;

    @NotNull(message = "Не пустое")
    @Min(value = 0, message = "Минимальный вес 0")
    @Max(value = 300, message = "Максимальный вес 300")
    private int weight;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
