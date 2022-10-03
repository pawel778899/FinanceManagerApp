import modeldto.ExpenseDto;
import modeldto.IncomeDto;
import repository.CategoryRepository;
import repository.ExpenseRepository;
import repository.IncomeRepository;
import service.CategoryService;
import service.ExpenseService;
import service.IncomeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Set;

public class FinanceManagerControl {

    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService categoryService = new CategoryService(categoryRepository);
    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository);
    private static final IncomeRepository incomeRepository = new IncomeRepository();
    private static final IncomeService incomeService = new IncomeService(incomeRepository);


    // stałe do kontrolowania programu
    private static final int EXIT = 0;
    private static final int ADD_EXPENSE = 1;
    private static final int ADD_INCOME = 2;
    private static final int DELETE_EXPENSE = 3;
    private static final int DELETE_INCOME = 4;
    private static final int DISPLAY_ALL_EXPENSES_AND_INCOMES = 5;
    private static final int DISPLAY_ALL_EXPENSES = 6;
    private static final int DISPLAY_ALL_INCOMES = 7;
    private static final int DISPLAY_BALANCE = 8;
    private static final int DISPLAY_ALL_EXPENSES_GRUPING_BY_CATEGORY = 9;
    private static final int DISPLAY_ALL_EXPENSES_BETWEEN_DATES = 10;
    private static final int ADD_NEW_CATEGORY = 11;
    private static final int DELETE_CATEGORY = 12;

    private Scanner in = new Scanner(System.in);
    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = getInt();
            switch (option) {
                case EXIT -> exit();
                case ADD_EXPENSE -> addExpenseMenu();
                case ADD_INCOME -> addIncomeMenu();
                case DELETE_EXPENSE -> deleteExepenseMenu();
                case DELETE_INCOME -> deletedIncomeMenu();
                //case DISPLAY_ALL_EXPENSES_AND_INCOMES -> displayAllExpensesAndIncomes();
                case DISPLAY_ALL_EXPENSES -> displayAllExpenses();
                case DISPLAY_ALL_INCOMES -> displayAllIncomes();
                //case DISPLAY_BALANCE -> displayAllBalance();
                //case DISPLAY_ALL_EXPENSES_GRUPING_BY_CATEGORY -> displayAllExpensesGroupingByCategory();
                //case DISPLAY_ALL_EXPENSES_BETWEEN_DATES -> displayAllExpensesBetweenDates();
                case ADD_NEW_CATEGORY -> addNewCategory();
                case DELETE_CATEGORY -> deleteCategory();
                default -> System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while (option != EXIT);
    }


    public void printOptions() {

        System.out.println("Type the operation to execution: ");
        System.out.println(EXIT + " - Exit");
        System.out.println(ADD_EXPENSE + " - Add expense");
        System.out.println(ADD_INCOME + " - Add income");
        System.out.println(DELETE_EXPENSE + " - Delete expense");
        System.out.println(DELETE_INCOME + " - Delete income");
        System.out.println(DISPLAY_ALL_EXPENSES_AND_INCOMES + " - Display all expenses and incomes");
        System.out.println(DISPLAY_ALL_EXPENSES + " - Display all expenses");
        System.out.println(DISPLAY_ALL_INCOMES + " - Display all incomes");
        System.out.println(DISPLAY_BALANCE + " - Display balance");
        System.out.println(DISPLAY_ALL_EXPENSES_GRUPING_BY_CATEGORY + " - Display all expenses grouping by category");
        System.out.println(DISPLAY_ALL_EXPENSES_BETWEEN_DATES + " - Display all expenses between dates");
        System.out.println(ADD_NEW_CATEGORY + " - Add new category");
        System.out.println(DELETE_CATEGORY + " - Delete category");
    }

    public void addExpenseMenu() {
        System.out.println("Type expense amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(in.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
        in.nextLine();
        System.out.println("Type expense category: ");
        String category = in.nextLine();
        System.out.println("Type comment (optionally): ");
        String comment = in.nextLine();
        ExpenseDto expenseDto = new ExpenseDto(totalCost, comment, category);
        expenseService.addExpense(expenseDto);
    }

    public void addIncomeMenu() {
        System.out.println("Type income amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(in.nextBigDecimal()));
        System.out.println("Type comment (optionally): ");
        String comment = in.next();
        IncomeDto incomeDto = new IncomeDto(totalCost, comment);
        incomeService.addIncome(incomeDto);
    }

    public void deleteExepenseMenu() {
        System.out.println("Type expense id which you want to delete: ");
        Long expensedToBeDeleted = in.nextLong();
        expenseService.deleteExpense(expensedToBeDeleted);
    }

    public void deletedIncomeMenu() {
        System.out.println("Type income id which you want to delete: ");
        Long incomeIdToBeDeleted = in.nextLong();
        incomeService.deleteIncome(incomeIdToBeDeleted);
    }

    //public void displayAllBalance() {}
    public void displayAllExpenses() {
        Set<ExpenseDto> expenses = expenseService.getExpenses();
        System.out.println(expenses);
    }

    //public void displayAllExpensesAndIncomes (){}
    public void displayAllIncomes() {
        Set<IncomeDto> incomes = incomeService.getIncomes();
        System.out.println((incomes));
    }
    //public void displayAllBalance() {}

//    public void displayAllExpensesGroupingByCategory() {}
//    public void displayAllExpensesBetweenDates() {}

    public void addNewCategory() {
        System.out.println("Type category name: ");
        String categoryName = in.nextLine();
        categoryService.addCategory(categoryName);
    }

    public void deleteCategory() {
        System.out.println("Delete category name: ");
        String categoryName = in.nextLine();
        categoryService.deleteCategory(categoryName);
    }

    private void exit() {
        System.out.println("Koniec programu, papa!");
        // zamykamy strumień wejścia
        close();
    }

    public void close() {
        in.close();
    }

    public int getInt() {
        int number = in.nextInt();
        in.nextLine();
        return number;
    }
}