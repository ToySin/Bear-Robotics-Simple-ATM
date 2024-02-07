package atm.simple.service;

import atm.simple.entity.Account;
import atm.simple.entity.Card;
import atm.simple.repository.account.AccountRepository;
import atm.simple.repository.account.MemoryAccountRepositoryImpl;

public class AccountService {

    private final AccountRepository accountRepository = new MemoryAccountRepositoryImpl();

    public void createAccount(String accountNumber, Card card) {
        Account newAccount = Account.AccountBuilder.builder()
                .accountNumber(accountNumber)
                .build();
        card.addAccount(newAccount);
        accountRepository.save(newAccount);
    }

    public Account getAccount(String accountNumber) {
        Account result = accountRepository.findByAccountNumber(accountNumber);
        if (result == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return result;
    }

    public int deposit(String accountNumber, int amount) {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
        accountRepository.save(account);
        return getBalance(accountNumber);
    }

    public int withdraw(String accountNumber, int amount) {
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
        accountRepository.save(account);
        return getBalance(accountNumber);
    }

    public int getBalance(String accountNumber) {
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }
}
