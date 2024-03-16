package com.aomaraie.cloudnotes.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Note {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Note() {
        this("Default Title", "Default Content");
    }

    public Note (String title, String content) {
        this.title = title;
        this.content = content;
    }

    @PrePersist
    public void onCreate() {
        date = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
