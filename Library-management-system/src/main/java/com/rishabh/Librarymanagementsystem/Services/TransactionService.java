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
import java.util.concurrent.TimeUnit;


@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static Integer MAX_NO_OF_ISSUED_BOOKS = 3;
    public static Integer FINE_PER_DAY = 10;

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
        Long timeInMsOfCardValidity = card.getValidity().getTime();
        Long currTimeInMs = System.currentTimeMillis();
        if(timeInMsOfCardValidity < currTimeInMs){
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

    private String returnBook(Integer bookId,Integer cardId){
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        //calculate fine amount
        Long timeDiffernceInMs = System.currentTimeMillis() - transaction.getIssueDate().getTime();

        //This time is in MS, we need to convert this to days
        Long days = TimeUnit.DAYS.convert(timeDiffernceInMs,TimeUnit.MILLISECONDS);

        Integer fineAmt = 0;

        if(days>15)
            fineAmt = Math.toIntExact((days-15)*FINE_PER_DAY);
        
        
        // update the book status in all the tables
        transaction.setFineAmount(fineAmt);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date()); //automatically set the current date of your system
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);
        book.setIsIssued(false);

        transactionRepository.save(transaction);
        cardRepository.save(card);
        bookRepository.save(book);
        return "book has been returned successfully";
    }
}
