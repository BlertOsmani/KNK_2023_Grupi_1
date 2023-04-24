package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/KNKProjekti";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfull");
        }
        return connection;
    }

    public static void main(String[]args) throws SQLException {
        System.out.println(getConnection());
    }
}

