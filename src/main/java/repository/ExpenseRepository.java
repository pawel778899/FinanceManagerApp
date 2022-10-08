package repository;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;
import model.Expense;

import java.time.LocalDate;
import java.util.List;

public class ExpenseRepository {
    public void insert(Expense expense) {
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

    public List<Object[]> findExpenseSummaryGroupByCategory() { // ?????????????????
        EntityManager entityManager = ConnectionManager.getEntityManager();
        String sql = "SELECT SUM(e.amount), c.name, count(e.id) FROM Expense e " +
                "JOIN Category c ON e.category_id = c.id " +
                "GROUP BY c.name " +
                "ORDER BY SUM(e.amount) DESC";

        List<Object[]> resultList = entityManager.createNativeQuery(sql).getResultList();
        entityManager.close();
        return resultList;
    }
    public List<Expense> findBetweenDates(LocalDate start, LocalDate end) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<Expense> expenses = entityManager.createQuery("SELECT e FROM Expense e WHERE e.expanseAddDate>=?1 AND e.expanseAddDate<=?2")
                .setParameter(1, start)
                .setParameter(2, end)
                .getResultList();
        entityManager.close();
        return expenses;
    }

}
