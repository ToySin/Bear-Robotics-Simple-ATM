package atm.simple.entity;

import java.util.HashMap;
import java.util.Map;

public class Card {

    private String cardNumber;

    private String PIN;
    private final Map<String, Account> accounts = new HashMap<>();

    public boolean verifyPIN(String PIN) {
        return this.PIN.equals(PIN);
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }


    /**
     * Builder for Card
     */
    private Card() {
    }

    public static class CardBuilder {
        private final Card card;

        private CardBuilder() {
            card = new Card();
        }

        public static CardBuilder builder() {
            return new CardBuilder();
        }

        public CardBuilder cardNumber(String cardNumber) {
            card.cardNumber = cardNumber;
            return this;
        }

        public CardBuilder pin(String PIN) {
            card.PIN = PIN;
            return this;
        }

        public Card build() {
            return card;
        }
    }



    public String getCardNumber() {
        return cardNumber;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
