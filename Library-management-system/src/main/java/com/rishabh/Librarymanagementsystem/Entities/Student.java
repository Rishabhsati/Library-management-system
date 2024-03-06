package com.rishabh.Librarymanagementsystem.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity // this is the schema how tables will look like
@Table(name = "student_info")
@Setter
@Getter
@NoArgsConstructor  //default constructor
@AllArgsConstructor // all args constructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(nullable = false)
    private String name;

    private String branch;

    @Column(nullable = false)
    private double cgpa;

    @Column(unique = true)
    private String emailId;

}
