package com.rishabh.Librarymanagementsystem.Controllers;

import com.rishabh.Librarymanagementsystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/GenerateCards")
    public ResponseEntity generateCards(){
        String result = cardService.genrateCards();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/associateCardWithStudent")
    public ResponseEntity associateCardWithStudent(@RequestParam("cardId")Integer cardId,
                                                  @RequestParam("studentId")Integer studentId){
        String result = cardService.associateCardWithStudent(cardId,studentId);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}
