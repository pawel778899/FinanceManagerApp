import account.Account;
import account.AccountRepository;
import account.AccountService;
import category.CategoryRepository;
import category.CategoryService;
import config.ConnectionManager;
import expense.ExpenseDto;
import expense.ExpenseRepository;
import expense.ExpenseService;

import income.IncomeDto;
import income.IncomeRepository;
import income.IncomeService;
import jakarta.persistence.EntityManager;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Set;

public class Main {


    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService categoryService = new CategoryService(categoryRepository);
    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository);
    private static final IncomeRepository incomeRepository = new IncomeRepository();
    private static final IncomeService incomeService = new IncomeService(incomeRepository);
    private static final AccountRepository accountRepository = new AccountRepository();
    private static final AccountService accountService = new AccountService(accountRepository);


    public static void main(String[] args) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.close();


        ConnectionManager.getEntityManager();

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Type the operation to execution: ");

            System.out.println("0 - Exit");
            System.out.println("1 - Add expense");
            System.out.println("2 - Add income");
            System.out.println("3 - Delete expense");
            System.out.println("4 - Delete income");
            System.out.println("5 - Display all expenses and incomes");
            System.out.println("6 - Display all expenses");
            System.out.println("7 - Display all incomes");
            System.out.println("8 - Display balance");
            System.out.println("9 - Display all expenses grouping by category");
            System.out.println("10 - Display all expenses between dates");
            System.out.println("11 - Add new category");
            System.out.println("12 - Delete category");
            System.out.println("13 - Add account");
            System.out.println("14 - Delete account");
            System.out.println("15 - Display all accounts");



            int result = in.nextInt();
            in.nextLine();

            switch (result) {
                case 0 -> {
                    System.exit(0);
                }
                case 1 -> {
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
                case 2 -> {
                    System.out.println("Type income amount: ");
                    BigDecimal totalCost = new BigDecimal(String.valueOf(in.nextBigDecimal()));
                    System.out.println("Type comment (optionally): ");
                    String comment = in.next();
                    IncomeDto incomeDto = new IncomeDto(totalCost, comment);
                    incomeService.addIncome(incomeDto);
                }
                case 3 -> {
                    System.out.println("Type expense id which you want to delete: ");
                    Long expensedToBeDeleted = in.nextLong();
                    expenseService.deleteExpense(expensedToBeDeleted);
                }
                case 4 -> {
                    System.out.println("Type income id which you want to delete: ");
                    Long incomeIdToBeDeleted = in.nextLong();
                    incomeService.deleteIncome(incomeIdToBeDeleted);
                }
                case 6 -> {
                    Set<ExpenseDto> expenses = expenseService.getExpenses();
                    System.out.println(expenses);
                }
                case 7 -> {
                    Set<IncomeDto> incomes = incomeService.getIncomes();
                    System.out.println((incomes));
                }


                case 11 -> {
                    System.out.println("Type category name: ");
                    String categoryName = in.nextLine();
                    categoryService.addCategory(categoryName);
                }
                case 12 -> {
                    System.out.println("Delete category name: ");
                    String categoryName = in.nextLine();
                    categoryService.deleteCategory(categoryName);
                }
                case 13 -> {
                    System.out.println("Add account: ");
                    String accountName = in.nextLine();
                    accountService.addAccount(accountName);
                }
                case 14 -> {
                    System.out.println("Delete account name: ");
                    String accountName = in.nextLine();
                    accountService.deleteAccount(accountName);
                }
                case 15 -> {
                    Set<Account> accounts = accountService.getAccount();
                    System.out.println(accounts.toString());
                }

                default -> System.out.println("Choose number from 0 - 15 !!!!! ");
            }
        }
    }
}


