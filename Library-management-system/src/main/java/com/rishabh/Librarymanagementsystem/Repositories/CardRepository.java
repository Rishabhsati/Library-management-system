package com.rishabh.Librarymanagementsystem.Repositories;

import com.rishabh.Librarymanagementsystem.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
