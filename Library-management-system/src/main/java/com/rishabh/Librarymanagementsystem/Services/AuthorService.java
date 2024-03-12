package com.rishabh.Librarymanagementsystem.Services;

import com.rishabh.Librarymanagementsystem.Entities.Author;
import com.rishabh.Librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author) {
        author.setNoOfBooks(0);
        authorRepository.save(author);
        return "Author data has been saved successfully";
    }

    public Author getMaxAuthor() {
        List<Author> authorList = authorRepository.findAll();
        int max = 0;
        Author author1 = null;
        for(Author author : authorList){
            if(author.getNoOfBooks()>max)
            {
                max= author.getNoOfBooks();
                author1 = author;
            }
        }
        return author1;
    }
}
