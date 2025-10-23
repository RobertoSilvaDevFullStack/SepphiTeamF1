package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private String hostname;
    private String user;
    private String password;

    public ConnectDB(String hostname, String user, String password, String db) {

        this.hostname = "jdbc:postgresql://"+ hostname + ":5432/" + db; // ex: "jdbc:mysql://localhost:3306/f1db"
        this.user = user;
        this.password = password;
    }

    // Método de instância que usa as credenciais armazenadas
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(hostname, user, password);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }

    public static Connection getConnection(String hostname, String user, String password, String db) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+ hostname + ":5432/" + db, user, password);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }


}
