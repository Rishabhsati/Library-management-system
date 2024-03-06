package com.rishabh.Librarymanagementsystem.Controllers;

import com.rishabh.Librarymanagementsystem.Services.TransactionService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("issueBook")
    public String issueBook(@RequestParam("bookId")Integer bookId,
                            @RequestParam("cardId")Integer cardId){
        try {
            String res = transactionService.issueBook(bookId, cardId);
            return res;
        }
        catch (Exception e){
           return e.getMessage();
        }
    }
}
