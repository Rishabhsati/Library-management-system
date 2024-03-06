package com.rishabh.Librarymanagementsystem.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String authorName;

    private Integer age;

    private String emailId;

    private double rating;

//  @column default value 0
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer noOfBooks;
}
