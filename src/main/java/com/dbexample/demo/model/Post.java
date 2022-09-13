package com.dbexample.demo.model;

import javax.persistence.*;

@Entity
public class Post
{
    public Post()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public Post(String title, String anons, String fullText, User user) {
        this.title = title;
        Anons = anons;
        FullText = fullText;
        this.user = user;
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
