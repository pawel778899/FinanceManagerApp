import category.CategoryRepository;
import category.CategoryService;
import config.ConnectionManager;
import expense.ExpenseDto;
import expense.ExpenseRepository;
import expense.ExpenseService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {


    private static CategoryRepository categoryRepository = new CategoryRepository();

    private static CategoryService categoryService = new CategoryService(categoryRepository);
    private static ExpenseRepository expenseRepository = new ExpenseRepository();

    private static final ExpenseService expenseService = new ExpenseService(expenseRepository,categoryRepository);


    public static void main(String[] args) {
        ConnectionManager.getEntityManager();

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Type the operation to execution: ");

            System.out.println("1 - Add expense");


            int result = in.nextInt();
            switch (result) {
                case 1 -> {
                    System.out.println("Type expense amount: ");
                    BigDecimal totalCost = new BigDecimal(String.valueOf(in.nextBigDecimal())).setScale(2, RoundingMode.CEILING);
                    in.nextLine();
                    System.out.println("Type expense category: ");
                    String category = in.nextLine();
                    System.out.println("Type comment (optionally): ");
                    String comment = in.nextLine();
                    ExpenseDto expenseDto = new ExpenseDto(totalCost,comment,category);
                    expenseService.addExpense(expenseDto);

                    }

                }
            }
        }
    }

