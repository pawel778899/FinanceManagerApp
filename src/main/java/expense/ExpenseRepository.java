package expense;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

public class ExpenseRepository {
    public void insert(Expense expense){
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(expense);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
