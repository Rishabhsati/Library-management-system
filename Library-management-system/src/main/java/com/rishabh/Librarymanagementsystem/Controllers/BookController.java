package com.rishabh.Librarymanagementsystem.Controllers;

import com.rishabh.Librarymanagementsystem.Entities.Book;
import com.rishabh.Librarymanagementsystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book){
        try{
            String res = bookService.addBook(book);
            return new ResponseEntity(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/associateBookAndAuthor")
    public String associateBookAndAuthor(@RequestParam("authorId")Integer authorId,
                                         @RequestParam("bookId")Integer bookId){
        try{
            String res = bookService.associateBookAndAuthor(authorId,bookId);
            return res;
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getBooksByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam("authorId")Integer authorId){
        List<Book> ans = bookService.findBooksByAuthor(authorId);
        return ans;
    }
}
