package com.rishabh.Librarymanagementsystem.Repositories;

import com.rishabh.Librarymanagementsystem.Entities.Book;
import com.rishabh.Librarymanagementsystem.Entities.LibraryCard;
import com.rishabh.Librarymanagementsystem.Entities.Transaction;
import com.rishabh.Librarymanagementsystem.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {

    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
