package service;

import modeldto.ExpenseDto;
import model.Category;
import repository.CategoryRepository;
import model.Expense;
import repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public void addExpense(ExpenseDto expenseDto) {
        Category byName = categoryRepository.findByName(expenseDto.getCategory());
        Expense expense = new Expense();
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(byName);
        expense.setExpenseAddDate(LocalDate.now());
        expense.setComment(expenseDto.getComment());
        expenseRepository.insert(expense);
    }
    public void deleteExpense(Long expensedToBeDeleted) {
        if (expensedToBeDeleted != null) {
            expenseRepository.deleteById(expensedToBeDeleted);
        }
    }
    public Set<ExpenseDto> getExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(expense -> new ExpenseDto(expense.getAmount(),expense.getComment(), expense.getCategory().getName()))
                .collect(Collectors.toSet());
    }
}



