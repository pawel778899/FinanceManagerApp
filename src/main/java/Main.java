import category.CategoryRepository;
import category.CategoryService;
import config.ConnectionManager;
import expense.ExpenseDto;
import expense.ExpenseRepository;
import expense.ExpenseService;

import jakarta.persistence.EntityManager;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {


    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final CategoryService categoryService = new CategoryService(categoryRepository);
    private static final ExpenseRepository expenseRepository = new ExpenseRepository();
    private static final ExpenseService expenseService = new ExpenseService(expenseRepository, categoryRepository);


    public static void main(String[] args) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.close();


        ConnectionManager.getEntityManager();

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Type the operation to execution: ");

            System.out.println("1 - Add expense");
            System.out.println("11 - Add new category");
            System.out.println("12 - Delete new category");


            int result = in.nextInt();
            in.nextLine();


            switch (result) {
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
            }
        }
    }
}



