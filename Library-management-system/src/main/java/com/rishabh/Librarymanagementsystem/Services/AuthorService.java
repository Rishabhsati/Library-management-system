package com.rishabh.Librarymanagementsystem.Services;

import com.rishabh.Librarymanagementsystem.Entities.Author;
import com.rishabh.Librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author) {
        author.setNoOfBooks(0);
        authorRepository.save(author);
        return "Author data has been saved successfully";
    }
}
