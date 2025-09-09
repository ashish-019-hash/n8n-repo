package com.carddemo.controller;

import com.carddemo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public String getAllCards() {
        return "Card Controller - Get All Cards";
    }

    @GetMapping("/{id}")
    public String getCard(@PathVariable Long id) {
        return "Card Controller - Get Card: " + id;
    }

    @PostMapping
    public String createCard() {
        return "Card Controller - Create Card";
    }

    @PutMapping("/{id}")
    public String updateCard(@PathVariable Long id) {
        return "Card Controller - Update Card: " + id;
    }
}
