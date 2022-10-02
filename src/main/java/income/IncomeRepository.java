package income;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

public class IncomeRepository {

    public void insert(Income income) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(income);
        entityManager.getTransaction().commit();
        entityManager.close();
    }




}
