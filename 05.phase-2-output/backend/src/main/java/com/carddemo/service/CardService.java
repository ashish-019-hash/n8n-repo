package com.carddemo.service;

import com.carddemo.entity.Card;
import com.carddemo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardByNumber(String cardNum) {
        return cardRepository.findById(cardNum);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCard(String cardNum) {
        cardRepository.deleteById(cardNum);
    }
}
