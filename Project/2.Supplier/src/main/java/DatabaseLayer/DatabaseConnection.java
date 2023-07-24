package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private final String URL = "jdbc:mysql://localhost:3306/Supplier";
    private final String Uname = "root";
    private final String Password = "";

    private static DatabaseConnection instance;
    private Connection con;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, Uname, Password);
            System.out.println("Connection Success");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver class Error: " + ex.getMessage());
        }
    }

    public static DatabaseConnection getSingleInstance() throws SQLException {
        try {
            if (instance == null || instance.con.isClosed()) {
                instance = new DatabaseConnection();
            }
            return instance;
        } catch (SQLException ex) {
            throw new SQLException("Database Connection ERROR: " + ex.getMessage());
        }
    }

    public boolean executeQuery(String sqlQ) {
        try (Statement st = con.createStatement()) {
            int result = st.executeUpdate(sqlQ);
            return result > 0;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException ex) {
            System.out.println("Error while closing the connection: " + ex.getMessage());
        }
    }
}
