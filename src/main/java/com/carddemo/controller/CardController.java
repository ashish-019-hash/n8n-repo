package com.carddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @GetMapping
    public String getAllCards() {
        return "Card Controller - Placeholder for card operations";
    }

}
