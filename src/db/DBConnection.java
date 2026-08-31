package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // 1. Static variable to hold the single connection instance
    private static Connection connection;

    // 2. Private constructor prevents anyone else from creating a new DBConnection object
    private DBConnection() {
    }

    // 3. Public static method to get the single connection
    public static Connection getConnection() {
        try {
            // If the connection doesn't exist YET, or if the DAO closed it, create a new one
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost/dental_clinic_db",
                        "root",                                         
                        ""                                              
                );
                System.out.println("Database connected successfully!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        
        // Return the active connection
        return connection;
    }
}