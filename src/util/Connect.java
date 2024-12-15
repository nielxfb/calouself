package util;

import java.sql.*;

/**
 * Utility class for managing database connections and executing SQL queries.
 */
public final class Connect {

    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "calouself";
    private final String HOST = "localhost:3306";
    private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    private Connection con;
    private Statement st;
    private static Connect connect;

    /**
     * Private constructor to initialize the database connection.
     */
    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect the database, the system is terminated!");
            System.exit(0);
        }
    }

    /**
     * Provides a synchronized instance of the Connect class.
     *
     * @return a singleton instance of the Connect class
     */
    public static synchronized Connect getConnection() {
        return connect = (connect == null) ? new Connect() : connect;
    }

    /**
     * Executes a SQL query and returns the result set.
     *
     * @param query the SQL query to execute
     * @return the result set of the executed query
     */
    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Executes a SQL update statement.
     *
     * @param query the SQL update statement to execute
     */
    public void executeUpdate(String query) {
        try {
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}