//package io;
//
//import modeldto.ExpenseDto;
//import modeldto.IncomeDto;
//import repository.CategoryRepository;
//import repository.ExpenseRepository;
//import repository.IncomeRepository;
//import service.CategoryService;
//import service.ExpenseService;
//import service.IncomeService;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Scanner;
//import java.util.Set;
//
//public class DataReader {
//    private static final CategoryRepository categoryRepository = new CategoryRepository();
//    private static final CategoryService categoryService = new CategoryService(categoryRepository);
//    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
//    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository);
//    private static final IncomeRepository incomeRepository = new IncomeRepository();
//    private static final IncomeService incomeService = new IncomeService(incomeRepository);
//    private Scanner sc = new Scanner(System.in);
//
//    public void close() {
//        sc.close();
//    }
//
//    public int getInt() {
//        int number = sc.nextInt();
//        sc.nextLine();
//        return number;
//    }
//
//    public void addExpenseMenu() {
//        System.out.println("Type expense amount: ");
//        BigDecimal totalCost = new BigDecimal(String.valueOf(sc.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
//        sc.nextLine();
//        System.out.println("Type expense category: ");
//        String category = sc.nextLine();
//        System.out.println("Type comment (optionally): ");
//        String comment = sc.nextLine();
//        ExpenseDto expenseDto = new ExpenseDto(totalCost, comment, category);
//        expenseService.addExpense(expenseDto);
//    }

//    public void addIncomeMenu() {
//        System.out.println("Type income amount: ");
//        BigDecimal totalCost = new BigDecimal(String.valueOf(sc.nextBigDecimal()));
//        System.out.println("Type comment (optionally): ");
//        String comment = sc.next();
//        IncomeDto incomeDto = new IncomeDto(totalCost, comment);
//        incomeService.addIncome(incomeDto);
//    }
//
//    public void deleteExepenseMenu() {
//        System.out.println("Type expense id which you want to delete: ");
//        Long expensedToBeDeleted = sc.nextLong();
//        expenseService.deleteExpense(expensedToBeDeleted);
//    }
//    public void deletedIncomeMenu() {
//        System.out.println("Type income id which you want to delete: ");
//        Long incomeIdToBeDeleted = sc.nextLong();
//        incomeService.deleteIncome(incomeIdToBeDeleted);
//    }
//    //public void displayAllBalance() {}
//    public void displayAllExpenses() {
//        Set<ExpenseDto> expenses = expenseService.getExpenses();
//        System.out.println(expenses);
//    }
//    //public void displayAllExpensesAndIncomes (){}
//    public void displayAllIncomes() {
//        Set<IncomeDto> incomes = incomeService.getIncomes();
//        System.out.println((incomes));
//    }
    //public void displayAllBalance() {}

//    public void displayAllExpensesGroupingByCategory() {}
//    public void displayAllExpensesBetweenDates() {}

//    public void addNewCategory() {
//        System.out.println("Type category name: ");
//        String categoryName = sc.nextLine();
//        categoryService.addCategory(categoryName);
//    }
//    public void deleteCategory() {
//        System.out.println("Delete category name: ");
//        String categoryName = sc.nextLine();
//        categoryService.deleteCategory(categoryName);
//    }


//    public Book readAndCreateBook() {
//        System.out.println("Tytuł: ");
//        String title = sc.nextLine();
//        System.out.println("Autor: ");
//        String author = sc.nextLine();
//        System.out.println("Wydawnictwo: ");
//        String publisher = sc.nextLine();
//        System.out.println("ISBN: ");
//        String isbn = sc.nextLine();
//        System.out.println("Rok wydania: ");
//        int releaseDate = getInt();
//        System.out.println("Ilość stron: ");
//        int pages = getInt();
//
//        return new Book(title, author, releaseDate, pages, publisher, isbn);
//    }


//}