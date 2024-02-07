package atm.simple;

import atm.simple.controller.ATMController;
import atm.simple.entity.Card;
import atm.simple.repository.account.AccountRepository;
import atm.simple.repository.account.MemoryAccountRepositoryImpl;
import atm.simple.repository.card.CardRepository;
import atm.simple.repository.card.MemoryCardRepositoryImpl;
import atm.simple.service.AccountService;
import atm.simple.service.CardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ATMTestDriver {

    private final ATMController atmController = new ATMController();
    private final CardService cardService = new CardService();
    private final AccountService accountService = new AccountService();

    private final CardRepository cardRepository = new MemoryCardRepositoryImpl();
    private final AccountRepository accountRepository = new MemoryAccountRepositoryImpl();

    @BeforeEach
    public void beforeTest1() {
        Card card1 = cardService.createCard("donshin-card1", "1234");
        accountService.createAccount("donshin-acnt1", card1);
        accountService.createAccount("donshin-acnt2", card1);

        accountService.deposit("donshin-acnt1", 1000);
        accountService.deposit("donshin-acnt2", 2000);

        System.out.println("Initial data: ");
        System.out.println("donshin-card1: " + card1.getCardNumber() + ", pin: 1234");
        System.out.println("donshin-acnt1: " + accountService.getBalance("donshin-acnt1"));
        System.out.println("donshin-acnt2: " + accountService.getBalance("donshin-acnt2"));
        System.out.println();
    }

    @AfterEach
    public void afterTest1() {
        ((MemoryCardRepositoryImpl) cardRepository).clearStore();
        ((MemoryAccountRepositoryImpl) accountRepository).clearStore();
        System.out.println("\n\n\n");
    }

    @Test
    public void testCase1_balance_and_deposit() {
        System.out.println("testCase1_balance_and_deposit\n");

        //The result of the following code is 1000. (beforeTest1 1000)
        System.out.println("Before deposit 1000 to donshin-acnt1: " +
                atmController.service(
                        cardService.getCard("donshin-card1"),
                        "1234",
                        "donshin-acnt1", "balance", 0)
        );
        Assertions.assertEquals(1000, atmController.service(
                cardService.getCard("donshin-card1"),
                "1234",
                "donshin-acnt1", "balance", 0)
        );

        //The result of the following code is 2000. (beforeTest1 1000 + 1000)
        System.out.println("After deposit 1000 to donshin-anct1: " +
                atmController.service(
                        cardService.getCard("donshin-card1"),
                        "1234",
                        "donshin-acnt1", "deposit", 1000)
        );
        Assertions.assertEquals(2000, atmController.service(
                cardService.getCard("donshin-card1"),
                "1234",
                "donshin-acnt1", "balance", 0)
        );


    }

    @Test
    public void testCase2_wrong_pin_number() {
        System.out.println("testCase2_wrong_pin_number\n");

        //The result of the following code is "Invalid PIN."
        System.out.println("Try to deposit with wrong pin number: 1235");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            atmController.service(
                    cardService.getCard("donshin-card1"),
                    "1235",
                    "donshin-acnt1", "deposit", 1000);
        });
    }

    @Test
    public void testCase3_multiple_deposit_and_withdraw() {
        System.out.println("testCase3_multiple_deposit_and_withdraw\n");

        //The result of the following code is 1000. (beforeTest1 1000)
        System.out.println("Before deposit 1000 to donshin-acnt1: " +
                atmController.service(
                        cardService.getCard("donshin-card1"),
                        "1234",
                        "donshin-acnt1", "balance", 0)
        );
        Assertions.assertEquals(1000, atmController.service(
                cardService.getCard("donshin-card1"),
                "1234",
                "donshin-acnt1", "balance", 0));

        //The result of the following code is 2000. (beforeTest1 1000 + 1000)
        System.out.println("After deposit 1000 to donshin-anct1: " +
                atmController.service(
                        cardService.getCard("donshin-card1"),
                        "1234",
                        "donshin-acnt1", "deposit", 1000)
        );
        Assertions.assertEquals(2000, atmController.service(
                cardService.getCard("donshin-card1"),
                "1234",
                "donshin-acnt1", "balance", 0));

        //The result of the following code is 1000. (beforeTest1 2000 - 1000)
        System.out.println("After withdraw 1000 from donshin-anct2: " +
                atmController.service(
                        cardService.getCard("donshin-card1"),
                        "1234",
                        "donshin-acnt2", "withdraw", 1000)
        );
        Assertions.assertEquals(1000, atmController.service(
                cardService.getCard("donshin-card1"),
                "1234",
                "donshin-acnt2", "balance", 0));
    }
}
