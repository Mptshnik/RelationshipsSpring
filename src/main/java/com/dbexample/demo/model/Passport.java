package com.dbexample.demo.model;

import javax.persistence.*;

@Entity
public class Passport
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @OneToOne(mappedBy = "passport")
    private Person person;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String number;
}
