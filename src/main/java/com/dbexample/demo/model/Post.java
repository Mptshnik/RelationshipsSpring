package com.dbexample.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post
{
    public Post()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return Anons;
    }

    public void setAnons(String anons) {
        Anons = anons;
    }

    public String getFullText() {
        return FullText;
    }

    public void setFullText(String fullText) {
        FullText = fullText;
    }

    public Post(String title, String anons, String fullText) {
        this.title = title;
        Anons = anons;
        FullText = fullText;
    }

    private String title, Anons, FullText;

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    private int Views;
}
