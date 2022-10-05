package Control;

import exception.NoSuchOptionException;
import io.ConsolePrinter;
import modeldto.ExpenseDto;
import modeldto.IncomeDto;
import modeldto.SummaryDto;
import repository.CategoryRepository;
import repository.ExpenseRepository;
import repository.IncomeRepository;
import service.CategoryService;
import service.ExpenseService;
import service.IncomeService;
import service.SummaryService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;



public class FinanceManagerControl {

    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService categoryService = new CategoryService(categoryRepository);
    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository);
    private static final IncomeRepository incomeRepository = new IncomeRepository();
    private static final IncomeService incomeService = new IncomeService(incomeRepository);
    private static final SummaryService summaryService = new SummaryService(expenseRepository, incomeRepository);


    //Static variables to control program
//    private static final int EXIT = 0;  - zakomentowane bo while (option != EXIT); mie działa EXIT = Option albo int
//    private static final int ADD_EXPENSE = 1;
//    private static final int ADD_INCOME = 2;
//    private static final int DELETE_EXPENSE = 3;
//    private static final int DELETE_INCOME = 4;
//    private static final int DISPLAY_ALL_EXPENSES_AND_INCOMES = 5;
//    private static final int DISPLAY_ALL_EXPENSES = 6;
//    private static final int DISPLAY_ALL_INCOMES = 7;
//    private static final int DISPLAY_BALANCE = 8;
//    private static final int DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY = 9;
//    private static final int DISPLAY_ALL_EXPENSES_BETWEEN_DATES = 10;
//    private static final int ADD_NEW_CATEGORY = 11;
//    private static final int DELETE_CATEGORY = 12;

    private final Scanner scanner = new Scanner(System.in);
    //    DataReader dataReader = new DataReader();
    private final ConsolePrinter printer = new ConsolePrinter();

    public void controlLoop() {
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case EXIT -> exitAppMenu();
                case ADD_EXPENSE -> addExpenseMenu();
                case ADD_INCOME -> addIncomeMenu();
                case DELETE_EXPENSE -> deleteExpenseMenu();
                case DELETE_INCOME -> deletedIncomeMenu();
                case DISPLAY_ALL_EXPENSES_AND_INCOMES -> displayAllExpensesAndIncomesMenu();
                case DISPLAY_ALL_EXPENSES -> displayAllExpensesMenu();
                case DISPLAY_ALL_INCOMES -> displayAllIncomesMenu();
                case DISPLAY_BALANCE -> displayAllBalanceMenu();
                //case DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY -> displayAllExpensesGroupingByCategory();
                //case DISPLAY_ALL_EXPENSES_BETWEEN_DATES -> displayAllExpensesBetweenDates();
                case ADD_NEW_CATEGORY -> addNewCategoryMenu();
                case DELETE_CATEGORY -> deleteCategoryMenu();
                default -> printer.printLine("Choose number from 0 - 12 !!!!! ");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", podaj ponownie:");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:");
            }
        }

        return option;
    }

    private void printOptions() {
        printer.printLine("[*******]>>>>>>>>>>Type the operation to execution: <<<<<<<<<<[***]");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

//    public void printOptions2() {
//
//        printLine("[***]>>>>>>>>>>Type the operation to execution: <<<<<<<<<<[***]");
//        printLine(EXIT + " - Exit");
//        printLine(ADD_EXPENSE + " - Add expense");
//        printLine(ADD_INCOME + " - Add income");
//        printLine(DELETE_EXPENSE + " - Delete expense");
//        printLine(DELETE_INCOME + " - Delete income");
//        printLine(DISPLAY_ALL_EXPENSES_AND_INCOMES + " - Display all expenses and incomes");
//        printLine(DISPLAY_ALL_EXPENSES + " - Display all expenses");
//        printLine(DISPLAY_ALL_INCOMES + " - Display all incomes");
//        printLine(DISPLAY_BALANCE + " - Display balance");
//        printLine(DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY + " - Display all expenses grouping by category");
//        printLine(DISPLAY_ALL_EXPENSES_BETWEEN_DATES + " - Display all expenses between dates");
//        printLine(ADD_NEW_CATEGORY + " - Add new category");
//        printLine(DELETE_CATEGORY + " - Delete category");
//    }

    public void addExpenseMenu() {
        printer.printLine("Type expense amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(scanner.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
        scanner.nextLine();
        printer.printLine("Type expense category: ");
        String category = scanner.nextLine();
        printer.printLine("Type comment (optionally): ");
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
        printer.printLine("Type income amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
        printer.printLine("Type comment (optionally): ");
        String comment = scanner.next();
        IncomeDto incomeDto = new IncomeDto(totalCost, comment);
        try {
            incomeService.addIncome(incomeDto);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteExpenseMenu() {
        printer.printLine("Type expense id which you want to delete: ");
        Long expenseToBeDeleted = scanner.nextLong();
        expenseService.deleteExpense(expenseToBeDeleted);
    }

    public void deletedIncomeMenu() {
        printer.printLine("Type income id which you want to delete: ");
        Long incomeIdToBeDeleted = scanner.nextLong();
        incomeService.deleteIncome(incomeIdToBeDeleted);
    }

    public void displayAllExpensesMenu() {
        Set<ExpenseDto> expenses = expenseService.getExpenses();
        printer.printLine(expenses.toString());
    }

    public void displayAllExpensesAndIncomesMenu() {
        SummaryDto summary = summaryService.getSummary();
        printer.printLine(String.valueOf(summary));
    }

    public void displayAllIncomesMenu() {
        Set<IncomeDto> incomes = incomeService.getIncomes();
        printer.printLine((incomes).toString());
    }

    public void displayAllBalanceMenu() {
        String balance = summaryService.getBalance();
        printer.printLine(balance + " zł");
    }

//    public void displayAllExpensesGroupingByCategory() {
//        SummaryExtendDtos summaryExtendDtos = summaryService.summaryExtendDtos();
//        printLine(summaryExtendDtos);
//
//    }
//    public void displayAllExpensesBetweenDates() {}

    public void addNewCategoryMenu() {
        printer.printLine("Type category name: ");
        String categoryName = scanner.nextLine();
        categoryService.addCategory(categoryName);
    }


    public void deleteCategoryMenu() {
        printer.printLine("Delete category name: ");
        String categoryName = scanner.nextLine();
        categoryService.deleteCategory(categoryName);
    }

    private void exitAppMenu() {
        printer.printLine("Koniec programu, papa!");
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

    private enum Option {

        EXIT(0, "Exit"),
        ADD_EXPENSE(1, "Add expense"),
        ADD_INCOME(2, "Add income"),
        DELETE_EXPENSE(3, "Delete expense"),
        DELETE_INCOME(4, "Delete income"),
        DISPLAY_ALL_EXPENSES_AND_INCOMES(5, "Display all expenses and incomes"),
        DISPLAY_ALL_EXPENSES(6, "Display all expenses"),
        DISPLAY_ALL_INCOMES(7, "Display all incomes"),
        DISPLAY_BALANCE(8, "Display balance"),
        DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY(9, "Display all expenses grouping by category"),
        DISPLAY_ALL_EXPENSES_BETWEEN_DATES(10, "Display all expenses between dates"),
        ADD_NEW_CATEGORY(11, "Add new category"),
        DELETE_CATEGORY(12, "Delete category");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + "------>   " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id " + option);
            }
        }


    }
}