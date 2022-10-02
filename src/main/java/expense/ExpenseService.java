package expense;

import category.Category;
import category.CategoryRepository;

import java.time.LocalDate;

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

}



