package income;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class IncomeRepository {

    public void insert(Income income) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(income);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void delete(Income income) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(income);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Income find(Long id) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        Income income = entityManager.find(Income.class, id);
        entityManager.close();
        return income;
    }
    public List<Income> findAll() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<Income> incomes = entityManager.createQuery("SELECT i FROM Income i").getResultList();
        entityManager.close();
        return incomes;
    }

}