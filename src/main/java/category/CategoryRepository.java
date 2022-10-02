package category;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

public class CategoryRepository {

    public Category findByName(String name) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        Category category = entityManager.createQuery("SELECT c from Category c WHERE c.name=:param", Category.class)
                .setParameter("param", name).getSingleResult();
        entityManager.close();
        return category;

    }

}
