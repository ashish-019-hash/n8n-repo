package com.carddemo.controller;

import com.carddemo.entity.Card;
import com.carddemo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{cardNum}")
    public ResponseEntity<Card> getCardByNumber(@PathVariable String cardNum) {
        Optional<Card> card = cardService.getCardByNumber(cardNum);
        return card.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }

    @PutMapping("/{cardNum}")
    public ResponseEntity<Card> updateCard(@PathVariable String cardNum, @RequestBody Card card) {
        card.setCardNum(cardNum);
        return ResponseEntity.ok(cardService.updateCard(card));
    }

    @DeleteMapping("/{cardNum}")
    public ResponseEntity<Void> deleteCard(@PathVariable String cardNum) {
        cardService.deleteCard(cardNum);
        return ResponseEntity.noContent().build();
    }
}
