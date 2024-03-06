package com.rishabh.Librarymanagementsystem.Repositories;

import com.rishabh.Librarymanagementsystem.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
