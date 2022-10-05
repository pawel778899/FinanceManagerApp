package service;

import model.Expense;
import model.Income;
import modeldto.*;
import repository.ExpenseRepository;
import repository.IncomeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SummaryService {

    private ExpenseRepository expenseRepository;
    private IncomeRepository incomeRepository;

    public SummaryService(ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }
    public SummaryDto getSummary() {
        List<Expense> expenses = expenseRepository.findAll();
        List<Income> incomes = incomeRepository.findAll();
        Set<PrintExpenseDto> expenseDtos = expenses.stream()
                .map(e -> new PrintExpenseDto(e.getId(), e.getAmount().toString() + " zł",e.getComment(), e.getCategory().getName(),
                        e.getExpenseAddDate().toString()))
                .collect(Collectors.toSet());
        Set<PrintIncomeDto> incomeDtos = incomes.stream()
                .map(i -> new PrintIncomeDto(i.getId(), i.getAmount().toString() + " zł", i.getComment(), i.getIncomeAddDate().toString()))
                .collect(Collectors.toSet());
        SummaryDto summaryDto = new SummaryDto(incomeDtos, expenseDtos);
        return summaryDto;
    }
    public String getBalance() {
        List<Expense> expenses = expenseRepository.findAll();
        List<Income> incomes = incomeRepository.findAll();
        BigDecimal expensesBalance = expenses.stream().map(expense -> expense.getAmount()).reduce(BigDecimal.ZERO, (bigDecimal, augend) -> bigDecimal.add(augend));
        BigDecimal incomeBalance = incomes.stream().map(income -> income.getAmount()).reduce(BigDecimal.ZERO, (bigDecimal, augend) -> bigDecimal.add(augend));
        BigDecimal balance = incomeBalance.subtract(expensesBalance);
        return balance.toString();
    }


//    public SummaryExtendDtos summaryExtendDtos() {
//        List<Object[]> expenseGroupByCategoryList = expenseRepository.findExpenseSummaryGroupByCategory();
//        SummaryExtendDtos summaryExtendDtos = new SummaryExtendDtos();
//        for (Object[] objects : expenseGroupByCategoryList) {
//            BigDecimal totalCost = (BigDecimal) objects[0];
//            String categoryName = (String) objects[1];
//            Long numberOfExpenses = (Long) objects[2];
//            SummaryExpenseDto summaryExpanseDto = new SummaryExpenseDto(categoryName, totalCost, numberOfExpenses);
//            summaryExtendDtos.getSummaryExpanseDtoList().add(summaryExpanseDto);
//        }
//
//        return summaryExtendDtos;
//    }
}
