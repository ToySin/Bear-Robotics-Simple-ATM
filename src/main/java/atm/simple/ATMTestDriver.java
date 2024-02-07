package atm.simple;

import atm.simple.controller.ATMController;
import atm.simple.entity.Card;
import atm.simple.service.AccountService;
import atm.simple.service.CardService;

public class ATMTestDriver {

    private final ATMController atmController = new ATMController();
    private final CardService cardService = new CardService();
    private final AccountService accountService = new AccountService();

    private void beforeTest1() {
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

    public void testCase1_balance_and_deposit() {
        System.out.println("\n\n\ntestCase1_balance_and_deposit");
        beforeTest1();

        //The result of the following code is 1000. (beforeTest1 1000)
        System.out.println("Before deposit 1000 to donshin-acnt1: " +
                atmController.service(
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
    }

    public void testCase2_wrong_pin_number() {
        System.out.println("\n\n\ntestCase2_wrong_pin_number");
        beforeTest1();

        //The result of the following code is "Invalid PIN."
        try {
            System.out.println("Try to deposit with wrong pin number: 1235");
            atmController.service(
                    cardService.getCard("donshin-card1"),
                    "1235",
                    "donshin-acnt1", "balance", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Result: " + e.getMessage());
        }
    }

    public void testCase3_multiple_deposit_and_withdraw() {
        System.out.println("\n\n\ntestCase3_multiple_deposit_and_withdraw");
        beforeTest1();

        //The result of the following code is 1000. (beforeTest1 1000)
        System.out.println("Before deposit 1000 to donshin-acnt1: " +
                atmController.service(
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

        //The result of the following code is 1000. (beforeTest1 2000 - 1000)
        System.out.println("After withdraw 1000 from donshin-anct2: " +
                atmController.service(
                        cardService.getCard("donshin-card1"),
                        "1234",
                        "donshin-acnt2", "withdraw", 1000)
        );
    }

    public static void main(String[] args) {
        ATMTestDriver atmTestDriver = new ATMTestDriver();

        atmTestDriver.testCase1_balance_and_deposit();
        atmTestDriver.testCase2_wrong_pin_number();
        atmTestDriver.testCase3_multiple_deposit_and_withdraw();
    }
}