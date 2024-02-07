package atm.simple.service;

import atm.simple.entity.Account;
import atm.simple.entity.Card;
import atm.simple.repository.card.CardRepository;
import atm.simple.repository.card.MemoryCardRepositoryImpl;

public class CardService {

    private final CardRepository cardRepository = new MemoryCardRepositoryImpl();

    public Card createCard(String cardNumber, String pin) {
        Card card = Card.CardBuilder.builder()
                .cardNumber(cardNumber)
                .pin(pin)
                .build();
        cardRepository.save(card);
        return card;
    }

    public Card getCard(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    public boolean verifyPIN(String cardNumber, String pin) {
        Card card = cardRepository.findByCardNumber(cardNumber);
        return card.verifyPIN(pin);
    }
}
