package Control;

import io.DataReader;
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

import static Control.Option.EXIT;

public class FinanceManagerControl {

    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService categoryService = new CategoryService(categoryRepository);
    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository);
    private static final IncomeRepository incomeRepository = new IncomeRepository();
    private static final IncomeService incomeService = new IncomeService(incomeRepository);


     //Static variables to control program
//    private static final int EXIT = 0;  - zakomentowane bo while (option != EXIT); mie działa EXIT = Option albo int
    private static final int ADD_EXPENSE = 1;
    private static final int ADD_INCOME = 2;
    private static final int DELETE_EXPENSE = 3;
    private static final int DELETE_INCOME = 4;
    private static final int DISPLAY_ALL_EXPENSES_AND_INCOMES = 5;
    private static final int DISPLAY_ALL_EXPENSES = 6;
    private static final int DISPLAY_ALL_INCOMES = 7;
    private static final int DISPLAY_BALANCE = 8;
    private static final int DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY = 9;
    private static final int DISPLAY_ALL_EXPENSES_BETWEEN_DATES = 10;
    private static final int ADD_NEW_CATEGORY = 11;
    private static final int DELETE_CATEGORY = 12;

    private Scanner scanner = new Scanner(System.in);
    public void controlLoop() {
        Option option;

        do {
            printOptions();
            option = Option.createFromInt(getInt());
            switch (option) {
                case EXIT -> exitAppMenu();
                case ADD_EXPENSE -> addExpenseMenu();
                case ADD_INCOME -> addIncomeMenu();
                case DELETE_EXPENSE -> deleteExpenseMenu();
                case DELETE_INCOME -> deletedIncomeMenu();
                //case DISPLAY_ALL_EXPENSES_AND_INCOMES -> displayAllExpensesAndIncomes();
                case DISPLAY_ALL_EXPENSES -> displayAllExpensesMenu();
                case DISPLAY_ALL_INCOMES -> displayAllIncomesMenu();
                //case DISPLAY_BALANCE -> displayAllBalance();
                //case DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY -> displayAllExpensesGroupingByCategory();
                //case DISPLAY_ALL_EXPENSES_BETWEEN_DATES -> displayAllExpensesBetweenDates();
                case ADD_NEW_CATEGORY -> addNewCategoryMenu();
                case DELETE_CATEGORY -> deleteCategoryMenu();
                default -> System.out.println("Choose number from 0 - 12 !!!!! ");
            }
        } while (option != EXIT);
    }


    private void printOptions() {
        System.out.println("[***]>>>>>>>>>>Type the operation to execution: <<<<<<<<<<[***]");
        for (Option value : Option.values()) {
            System.out.println(value);
        }
    }

    public void printOptions2() {

        System.out.println("[***]>>>>>>>>>>Type the operation to execution: <<<<<<<<<<[***]");
        System.out.println(EXIT + " - Exit");
        System.out.println(ADD_EXPENSE + " - Add expense");
        System.out.println(ADD_INCOME + " - Add income");
        System.out.println(DELETE_EXPENSE + " - Delete expense");
        System.out.println(DELETE_INCOME + " - Delete income");
        System.out.println(DISPLAY_ALL_EXPENSES_AND_INCOMES + " - Display all expenses and incomes");
        System.out.println(DISPLAY_ALL_EXPENSES + " - Display all expenses");
        System.out.println(DISPLAY_ALL_INCOMES + " - Display all incomes");
        System.out.println(DISPLAY_BALANCE + " - Display balance");
        System.out.println(DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY + " - Display all expenses grouping by category");
        System.out.println(DISPLAY_ALL_EXPENSES_BETWEEN_DATES + " - Display all expenses between dates");
        System.out.println(ADD_NEW_CATEGORY + " - Add new category");
        System.out.println(DELETE_CATEGORY + " - Delete category");
    }

    public void addExpenseMenu() {
        System.out.println("Type expense amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(scanner.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
        scanner.nextLine();
        System.out.println("Type expense category: ");
        String category = scanner.nextLine();
        System.out.println("Type comment (optionally): ");
        String comment = scanner.nextLine();
        ExpenseDto expenseDto = new ExpenseDto(totalCost, comment, category);
        try {
            expenseService.addExpense(expenseDto);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
/*IllegalArgumentException is a Java exception indicating that a method has received an argument that is invalid or inappropriate for this method's purposes.*/
    public void addIncomeMenu() {
        System.out.println("Type income amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
        System.out.println("Type comment (optionally): ");
        String comment = scanner.next();
        IncomeDto incomeDto = new IncomeDto(totalCost, comment);
        try {
            incomeService.addIncome(incomeDto);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    public void deleteExpenseMenu() {
        System.out.println("Type expense id which you want to delete: ");
        Long expenseToBeDeleted = scanner.nextLong();
        expenseService.deleteExpense(expenseToBeDeleted);
    }

    public void deletedIncomeMenu() {
        System.out.println("Type income id which you want to delete: ");
        Long incomeIdToBeDeleted = scanner.nextLong();
        incomeService.deleteIncome(incomeIdToBeDeleted);
    }

    //public void displayAllBalance() {}
    public void displayAllExpensesMenu() {
        Set<ExpenseDto> expenses = expenseService.getExpenses();
        System.out.println(expenses);
    }

    //public void displayAllExpensesAndIncomes (){}
    public void displayAllIncomesMenu() {
        Set<IncomeDto> incomes = incomeService.getIncomes();
        System.out.println((incomes));
    }
    //public void displayAllBalance() {}

//    public void displayAllExpensesGroupingByCategory() {}
//    public void displayAllExpensesBetweenDates() {}

    public void addNewCategoryMenu() {
        System.out.println("Type category name: ");
        String categoryName = scanner.nextLine();
        categoryService.addCategory(categoryName);
    }

    public void deleteCategoryMenu() {
        System.out.println("Delete category name: ");
        String categoryName = scanner.nextLine();
        categoryService.deleteCategory(categoryName);
    }

    private void exitAppMenu() {
        System.out.println("Koniec programu, papa!");
        close(); // close input stream
    }

    public void close() {
        scanner.close();
    }

    public int getInt() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }
}