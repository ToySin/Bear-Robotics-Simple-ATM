package atm.simple.entity;

public class Account {

    private String accountNumber;
    private int balance;

    public void deposit(int amount) {
        balance += amount;
    }

    /**
     * Maybe it is available to withdraw more than the balance!
     * It is a feature for overdraft account!
     */
    public void withdraw(int amount) {
        balance -= amount;
    }


    /**
     * Builder for Account
     */
    private Account() {
    }

    public static class AccountBuilder {
        private final Account account;

        private AccountBuilder() {
            account = new Account();
        }

        public static AccountBuilder builder() {
            return new AccountBuilder();
        }

        public AccountBuilder accountNumber(String accountNumber) {
            account.accountNumber = accountNumber;
            return this;
        }

        public Account build() {
            return account;
        }
    }



    public int getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
