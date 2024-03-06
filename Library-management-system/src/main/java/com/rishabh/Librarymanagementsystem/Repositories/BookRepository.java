package com.rishabh.Librarymanagementsystem.Repositories;

import com.rishabh.Librarymanagementsystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
