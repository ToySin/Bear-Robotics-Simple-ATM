package atm.simple.repository.card;

import atm.simple.entity.Card;

public interface CardRepository {

    void save(Card card);

    Card findByCardNumber(String cardNumber);
}
