package atm.simple.controller;

import atm.simple.entity.Account;
import atm.simple.entity.Card;
import atm.simple.service.AccountService;
import atm.simple.service.CardService;

import java.util.List;

public class ATMController {

    private final CardService cardService = new CardService();
    private final AccountService accountService = new AccountService();

    /**
     * This service is only for test.
     * When the system integrate with other system, then replace this service method.
     */
    public int service(Card card, String pin, String accountNumber, String menu, int amount) {
        if (!cardService.verifyPIN(card.getCardNumber(), pin)) {
            throw new IllegalArgumentException("Invalid PIN.");
        }
        return switch (menu) {
            case "deposit" -> accountService.deposit(accountNumber, amount);
            case "withdraw" -> accountService.withdraw(accountNumber, amount);
            case "balance" -> accountService.getBalance(accountNumber);
            default -> throw new IllegalArgumentException("Invalid menu.");
        };
    }

    /**
     * These methods can be used for the integration test.
     */

    // This method should be called before another methods.
    public void verifyCardPin(String cardNumber, String pin) {
        if (!cardService.verifyPIN(cardNumber, pin)) {
            throw new IllegalArgumentException("Invalid PIN.");
        }
    }

    // This method can be used for UI development.
    public List<String> getAccounts(String cardNumber, String pin) {
        Card card = cardService.getCard(cardNumber);
        return card.getAccounts().values()
                .stream()
                .map(Account::getAccountNumber)
                .toList();
    }

    public int deposit(String accountNumber, int amount) {
        return accountService.deposit(accountNumber, amount);
    }

    public int withdraw(String accountNumber, int amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    public int getBalance(String accountNumber) {
        return accountService.getBalance(accountNumber);
    }
}
