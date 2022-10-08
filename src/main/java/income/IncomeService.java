package income;

import account.Account;
import account.AccountRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IncomeService {

    private IncomeRepository incomeRepository;
    private AccountRepository accountRepository;

    public IncomeService(IncomeRepository incomeRepository, AccountRepository accountRepository) {
        this.incomeRepository = incomeRepository;
        this.accountRepository = accountRepository;
    }

    public void addIncome(IncomeDto incomeDto) {
        Income income = new Income(incomeDto.getAmount(), incomeDto.getComment());
        incomeRepository.insert(income);
    }
    public void deleteIncome(Long id) {
        Income income = incomeRepository.find(id);
        if (income != null) {
            incomeRepository.delete(income);
        }
    }
    public Set<IncomeDto> getIncomes() {
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream()
                .map(i->new IncomeDto(i.getAmount(), i.getComment()))
                .collect(Collectors.toSet());
    }
    public void addIncomeWithAccount(IncomeDto incomeDto) {
        Account byId = accountRepository.findById(incomeDto.getAccountId());
        Income income = new Income(incomeDto.getAmount(), incomeDto.getComment(),byId);
        incomeRepository.insert(income);
    }
}

