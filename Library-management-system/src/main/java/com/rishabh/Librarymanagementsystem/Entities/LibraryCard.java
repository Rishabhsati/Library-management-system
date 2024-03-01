package com.rishabh.Librarymanagementsystem.Entities;

import com.rishabh.Librarymanagementsystem.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "card_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBooksIssued;

    private Date validity;

    @JoinColumn // This tells that a new column is adding the LB
    @OneToOne  // mapping is one to one
    private Student student;
}
