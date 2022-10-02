package expense;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ExpenseRepository {
    public void insert(Expense expense){
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(expense);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(Long id) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        Expense expense = entityManager.find(Expense.class, id);
        if (expense != null) {
            entityManager.remove(expense);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Expense> findAll() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<Expense> expenses = entityManager.createQuery("SELECT e FROM Expense e").getResultList();
        entityManager.close();
        return expenses;
    }

}
