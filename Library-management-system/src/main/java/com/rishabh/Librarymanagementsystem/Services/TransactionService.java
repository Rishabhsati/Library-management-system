package com.rishabh.Librarymanagementsystem.Services;

import com.rishabh.Librarymanagementsystem.Entities.Book;
import com.rishabh.Librarymanagementsystem.Entities.LibraryCard;
import com.rishabh.Librarymanagementsystem.Entities.Transaction;
import com.rishabh.Librarymanagementsystem.Enums.TransactionStatus;
import com.rishabh.Librarymanagementsystem.Repositories.BookRepository;
import com.rishabh.Librarymanagementsystem.Repositories.CardRepository;
import com.rishabh.Librarymanagementsystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static Integer MAX_NO_OF_ISSUED_BOOKS = 3;

    public String issueBook(Integer bookId, Integer cardId) throws Exception{

        //Find book and card from repositories
        //Create the transaction Object
        //set the relevant attributes of transactionObject
        //Change the attributes of Card and Book Entity

        //1. Book should be valid :->
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new Exception("BookId entered incorrect");
        }
        Book book = optionalBook.get();

        //2. Library Card should be valid
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(optionalLibraryCard.isEmpty()){
            throw new Exception("cardId is invalid");
        }
        LibraryCard card = optionalLibraryCard.get();

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //3. book should not be already issued
        if(book.getIsIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Book is already issued to cardId "+card.getCardNo();
        }

        //4. Limit of card has exceeded
        if(card.getNoOfBooksIssued() > MAX_NO_OF_ISSUED_BOOKS){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Max limit of this card has exceeded";
        }

        //5. Check for if the card has expired its validity
        LocalDate currentDate = LocalDate.now();
        if(currentDate.isAfter(card.getValidity())){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "The card has been expired";
        }

        //  Happy flow
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIsIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

        //Rule is whatever has been modified : should be saved ??
        cardRepository.save(card);
        bookRepository.save(book);
        transaction = transactionRepository.save(transaction);
        return "The transaction has been completed with transactionId "+transaction.getTransactionId();
    }
}
