package com.carddemo.service;

import com.carddemo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public void getAllCards() {
    }

    public void getCardById(Long id) {
    }

    public void createCard() {
    }

    public void updateCard(Long id) {
    }
}
