package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyObUtils {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/kcs";
    private static final String  Name = "root";
    private static final String  Password = "";
    public static Connection createConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,Name,Password);
        } catch (SQLException e) {
            System.out.println("Prisijungti nepavyko" + e );
        }
        return connection;

    }
}
