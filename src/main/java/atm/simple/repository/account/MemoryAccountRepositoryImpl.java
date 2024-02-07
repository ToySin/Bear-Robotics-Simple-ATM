package atm.simple.repository.account;

import atm.simple.entity.Account;

import java.util.HashMap;
import java.util.Map;

public class MemoryAccountRepositoryImpl implements AccountRepository {

    private static final Map<String, Account> stores = new HashMap<>();


    @Override
    public void save(Account account) {
        stores.put(account.getAccountNumber(), account);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return stores.get(accountNumber);
    }
}
