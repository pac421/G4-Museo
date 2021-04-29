package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnect {
    /**
     * Driver
     */
    private static String driver;
    /**
     * Connection URL
     */
    private static String url;
    /**
     * Username
     */
    private static String username;
    /**
     * Password
     */
    private static String password;
    /**
     * Objet Connexion
     */
    private static Connection connection;

    public static void setAttributes() {
        try {
            Properties properties = new Properties();
            FileInputStream in = new FileInputStream("resources/db.properties");
            properties.load(in);
            in.close();

            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        setAttributes();
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
