import control.FinanceManagerControl;
import config.ConnectionManager;
import jakarta.persistence.EntityManager;

public class FinanceManagerApp {
    public static void main(String[] args) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.close();

        final String appName = "FinanceManager v3.0";
        System.out.println(appName);
        FinanceManagerControl financeManagerControl = new FinanceManagerControl();
        financeManagerControl.controlLoop();
    }
}


