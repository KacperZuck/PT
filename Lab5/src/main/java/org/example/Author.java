package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Author {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public void setName(String xd) {
        name = xd;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
