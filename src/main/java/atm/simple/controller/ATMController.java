package atm.simple.controller;

import atm.simple.entity.Card;
import atm.simple.service.AccountService;
import atm.simple.service.CardService;

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

}
