import org.apache.log4j.BasicConfigurator;

public class main {
    /**
     * Call interface create function
     * */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        new ManagerHelper().show();
    }
}