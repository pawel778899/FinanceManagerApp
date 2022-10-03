import config.ConnectionManager;
import jakarta.persistence.EntityManager;

public class FinanceManagerAppMenu {
    public static void main(String[] args) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.close();

        final String appName = "FinanceManager v1.0";
        System.out.println(appName);
        FinanceManagerControl financeManagerControl = new FinanceManagerControl();
        financeManagerControl.controlLoop();
    }
}


