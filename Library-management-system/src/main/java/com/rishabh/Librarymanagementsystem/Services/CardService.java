package com.rishabh.Librarymanagementsystem.Services;

import com.rishabh.Librarymanagementsystem.CardStatus;
import com.rishabh.Librarymanagementsystem.Entities.LibraryCard;
import com.rishabh.Librarymanagementsystem.Entities.Student;
import com.rishabh.Librarymanagementsystem.Repositories.CardRepository;
import com.rishabh.Librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String genrateCards() {
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card.setNoOfBooksIssued(0);

        Date expiryDate = new Date(1028,6,1);
        card.setValidity(expiryDate);
        card = cardRepository.save(card);
        return "The card has been generated with cardId "+card.getCardNo();
    }

    public String associateCardWithStudent(Integer cardId, Integer studentId) {
        LibraryCard libraryCard = cardRepository.findById(cardId).get();
        Student student = studentRepository.findById(studentId).get();
        libraryCard.setCardStatus(CardStatus.ISSUED);
        libraryCard.setStudent(student); //Indirectly setting the FK in LC table
        //but bcz we are playing with entities so we will have to set as per the entity
        cardRepository.save(libraryCard);
        return "The card and student has been associated";
    }
}
