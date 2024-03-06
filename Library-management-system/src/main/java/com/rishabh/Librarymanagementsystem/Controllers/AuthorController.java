package com.rishabh.Librarymanagementsystem.Controllers;

import com.rishabh.Librarymanagementsystem.Entities.Author;
import com.rishabh.Librarymanagementsystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("add")
    public ResponseEntity addAuthor(@RequestBody Author author){
        String res = authorService.addAuthor(author);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
