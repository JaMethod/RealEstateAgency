import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class manages connection to MySQL database.
 */
public final class MysqlConnector {
    //only one for application
    private static Connection connection;

    private MysqlConnector() {}

    /**
     * @return a Connection object
     */
    public static Connection getConnection(){
        return connection;
    }

    /**
     * Creates database connection
     * @param url - database url
     * @param user - database username
     * @param password - database password
     */
    public static void createConnection(String url, String user, String password) {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connection to " + url + " is successful");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Connection to " + url + " is already created");
        }
    }

    /**
     * Closes database connection
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection has been closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}