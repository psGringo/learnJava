package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(catalog = "library", name = "book")
@Getter
@Setter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "author_id")
    private int authorId;
    @Column(name = "publisher_id")
    private int publisherId;
    @Column(name = "publication_year")
    private int publicationYear;
    @Column(name = "isbn")
    private long isbn;
}
