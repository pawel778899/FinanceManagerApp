package control;

import exception.NoSuchOptionException;
import io.ConsolePrinter;
import model.Account;
import modeldto.*;
import repository.AccountRepository;
import repository.CategoryRepository;
import repository.ExpenseRepository;
import repository.IncomeRepository;
import service.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class FinanceManagerControl {

    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService categoryService = new CategoryService(categoryRepository);
    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
    private static final AccountRepository accountRepository = new AccountRepository();
    private static final AccountService accountService = new AccountService(accountRepository);
    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository, accountRepository);
    private static final IncomeRepository incomeRepository = new IncomeRepository();
    private static final IncomeService incomeService = new IncomeService(incomeRepository);
    private static final SummaryService summaryService = new SummaryService(expenseRepository, incomeRepository);


    private final Scanner scanner = new Scanner(System.in);

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
                case DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY -> displayAllExpensesGroupingByCategoryMenu();
                case DISPLAY_ALL_EXPENSES_BETWEEN_DATES -> displayAllExpensesBetweenDatesMenu();
                case ADD_NEW_CATEGORY -> addNewCategoryMenu();
                case DELETE_CATEGORY -> deleteCategoryMenu();
                case ADD_ACCOUNT -> addAccountMenu();
                case DELETE_ACCOUNT -> deleteAccountMenu();
                case DISPLAY_ALL_ACCOUNTS -> displayAllAccountsMenu();
                case ADD_INCOME_TO_ACCOUNT -> addIncomeToAccountMenu();
                case ADD_EXPENSE_TO_ACCOUNT -> addExpenseToAccountMenu();
                default -> printer.printLine("Choose number from 0 - 17 !!!!! ");
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
                printer.printLine(e.getMessage() + ", Type again:");
            } catch (InputMismatchException ignored) {
                printer.printLine("You entered a value that is not a number, please enter a numeric value from 0 - 17 !");
            }
        }
        return option;
    }

    private void printOptions() {
        printer.printLine("[*******]>>>>>>>>>>Type the operation to execution: <<<<<<<<<<[*******]");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

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

    public void displayAllExpensesGroupingByCategoryMenu() {
        SummaryExtendDtos summaryExtendDtos = summaryService.summaryExtendDtos();
        printer.printLine(String.valueOf(summaryExtendDtos));

    }

    public void displayAllExpensesBetweenDatesMenu() {
        printer.printLine("Type start and end date in format yyyy-mm-dd");
        printer.printLine("Start date: ");
        String startDate = scanner.next();
        printer.printLine("End date: ");
        String endDate = scanner.next();
        Set<PrintExpenseDto> expensesBetweenDate = expenseService.getExpensesBetweenDate(startDate, endDate);
        printer.printLine((expensesBetweenDate).toString());
    }

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

    public void addAccountMenu() {
        printer.printLine("Type account number: ");
        String accountNumber = scanner.nextLine();
        printer.printLine("Type account name: ");
        String accountName = scanner.nextLine();
        accountService.addAccount(accountName, accountNumber);
    }

    public void deleteAccountMenu() {
        printer.printLine("Delete account name: ");
        String accountName = scanner.nextLine();
        accountService.deleteAccount(accountName);
    }

    public void displayAllAccountsMenu() {
        Set<Account> accounts = accountService.getAccount();
        printer.printLine(accounts.toString());
    }

    public void addIncomeToAccountMenu() {
        System.out.println("To which account should the expenses be added?");
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        allAccounts.forEach(accountDto -> {
            System.out.println("Id: " + accountDto.getId() + " accountNumber " + accountDto.getAccountNumber() + " accountName " + accountDto.getName());
        });
        Long accountId = scanner.nextLong();
        System.out.println("Type income amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
        System.out.println("Type comment (optionally): ");
        String comment = scanner.next();
        IncomeDto incomeDto = new IncomeDto(totalCost, comment, accountId);
        incomeService.addIncomeWithAccount(incomeDto);
    }

    public void addExpenseToAccountMenu() {
        System.out.println("Which account ?: Type id.");
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        allAccounts.forEach(accountDto -> {
            System.out.println("Id: " + accountDto.getId() + " accountNumber " + accountDto.getAccountNumber() + " accountName " + accountDto.getName());
        });
        Long accountId = scanner.nextLong();
        System.out.println("Type expense amount: ");
        BigDecimal totalCost = new BigDecimal(String.valueOf(scanner.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
        scanner.nextLine();
        System.out.println("Type expense category: ");
        String category = scanner.nextLine();
        System.out.println("Type comment (optionally): ");
        String comment = scanner.nextLine();
        ExpenseDto expenseDto = new ExpenseDto(totalCost, comment, category, accountId);
        expenseService.addExpenseWithAccount(expenseDto);
    }

    private void exitAppMenu() {
        printer.printLine("The program has ended, Bye bye!");
        close(); // close input stream
    }

    public void close() {
        scanner.close();
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }

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
        DELETE_CATEGORY(12, "Delete category"),
        ADD_ACCOUNT(13, "Add account"),
        DELETE_ACCOUNT(14, "Delete account"),
        DISPLAY_ALL_ACCOUNTS(15, "Display all accounts"),
        ADD_INCOME_TO_ACCOUNT(16, "Add income to account"),
        ADD_EXPENSE_TO_ACCOUNT(17, "Add expense to account");
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
                throw new NoSuchOptionException("Choose  id from the list ! " + option);
            }
        }


    }
}