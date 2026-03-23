import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DatabaseHandler {
    String url = "jdbc:mysql://localhost:3306/SoftwareExam";
    static {
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }




}
