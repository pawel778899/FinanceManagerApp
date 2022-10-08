package account;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public void addAccount(String name) {
        Account account = new Account();
        account.setAccountNumber(name);
        accountRepository.insert(account);
    }
    public void deleteAccount(String accountName) {
        Account account = accountRepository.findByName(accountName);
        if (account != null && (account.getExpenses() == null || account.getExpenses().isEmpty())) {
            accountRepository.delete(account);
        }
    }
    public Set<Account> getAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> new Account(account.getId(),account.getAccountNumber(), account.getName()))
                .collect(Collectors.toSet());
    }
}
