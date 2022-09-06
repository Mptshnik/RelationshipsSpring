package com.dbexample.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal
{
    public Animal()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Animal(String name, String speed, String color, int age, int length) {
        this.name = name;
        this.speed = speed;
        this.color = color;
        this.age = age;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private String name, speed, color;
    private int age, length;
}
