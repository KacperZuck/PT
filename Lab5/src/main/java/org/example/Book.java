package org.example;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne
    private Author author;

    public void setTitle(String s) {
        title = s;
    }

    public void setAuthor(Author a) {
        author = a;
    }

    public String getTitle() {
        return title;
    }
    public Author getAuthor(){ return author;}
}
