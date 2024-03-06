package com.rishabh.Librarymanagementsystem.Repositories;

import com.rishabh.Librarymanagementsystem.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {

}
