package account;

import category.Category;
import config.ConnectionManager;
import expense.Expense;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AccountRepository {

    public void insert(Account account) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(account); //presist dodanie do bazy tak jak w sql insert into
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Account account) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(account);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Account> findAll() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<Account> accounts = entityManager.createQuery("SELECT a FROM Account a").getResultList();
        entityManager.close();
        return accounts;
    }

    public Account findByName(String name) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        Account account = entityManager.createQuery("SELECT a FROM Account a WHERE a.name=:param", Account.class)
                .setParameter("param", name).getSingleResult();
        entityManager.close();
        return account;
    }
}



