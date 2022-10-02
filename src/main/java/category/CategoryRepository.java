package category;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

public class CategoryRepository {


    public void insert(Category category) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(category); //presist dodanie do bazy tak jak w sql insert into
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void delete(Category category) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Category findByName(String name) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        Category category = entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:param", Category.class)
                .setParameter("param", name).getSingleResult();
        entityManager.close();
        return category;
    }


}


