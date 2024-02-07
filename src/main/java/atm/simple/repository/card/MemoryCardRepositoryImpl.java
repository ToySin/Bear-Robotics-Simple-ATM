package atm.simple.repository.card;

import atm.simple.entity.Card;

import java.util.HashMap;
import java.util.Map;


public class MemoryCardRepositoryImpl implements CardRepository{

    private static final Map<String, Card> stores = new HashMap<>();

    @Override
    public void save(Card card) {
        stores.put(card.getCardNumber(), card);
    }

    @Override
    public Card findByCardNumber(String cardNumber) {
        return stores.get(cardNumber);
    }

    public void clearStore() {
        stores.clear();
    }
}
