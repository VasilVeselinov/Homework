package Test3.Connection_To_The_Databases;

import java.sql.*;

public class DBManager {

    private static final String DB_LOCALHOST_IP = "127.0.0.1";
    private static final String DB_PORT_IP = "3306";
    private static final String DB_SCHEME_NAME = "";
    private static final String DB_USER_NAME = "";
    private static final String DB_PASSWORD = "";

    private static DBManager dbManager = new DBManager();
    private Connection connectionWithScarajERPSystem;

    private DBManager(){
        System.out.println("DBManager sey hello!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        this.connectionWithScarajERPSystem = this.createConnection();
    }

    public static DBManager getDbManager() {
        return dbManager;
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            DB_LOCALHOST_IP + ":" +
                            DB_PORT_IP + "/" +
                            DB_SCHEME_NAME,
                    DB_USER_NAME,
                    DB_PASSWORD);
            System.out.println("Successful login");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public Connection getConnectionWithScarajERPSystem() {
        return connectionWithScarajERPSystem;
    }
}
