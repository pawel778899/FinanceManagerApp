package income;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IncomeService {

    private IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
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
//    public Set<PrintIncomeDto> getIncomes() {
//        List<Income> incomes = incomeRepository.findAll();
//        return incomes.stream()
//                .map(income -> new PrintIncomeDto(income.getId(), income.getAmount().toString() + " zł", income.getComment(), income.getIncomeAddDate().toString()))
//                .collect(Collectors.toSet());
//    }
}

