package atm.simple.repository.account;

import atm.simple.entity.Account;

public interface AccountRepository {

    void save(Account account);

    Account findByAccountNumber(String accountNumber);
}
