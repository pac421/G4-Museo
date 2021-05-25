package dao;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MysqlConnect {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
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

    public static String tryAuthentication(String email, String password, Boolean rememberMe) {
        System.out.println("tryAuthentication starting..");
        System.out.println("param email : "+email);
        System.out.println("param password : "+password);
        System.out.println("param rememberMe : "+rememberMe);

        if(email.isEmpty())
            return "Veuillez entrer un email";

        if(password.isEmpty())
            return "Veuillez entrer un mot de passe";

        try {
            String sql = "SELECT * FROM `USER` WHERE email=? LIMIT 1";
            PreparedStatement statement = MysqlConnect.connect().prepareStatement(sql);
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            if(!result.next())
                return "Email invalide";

            String hashed_password = result.getString("password");
            if(!hashed_password.equals(password))
                return "Mot de passe invalide";

            FileWriter fileWriter = new FileWriter("credentials.txt");
            fileWriter.write(rememberMe ? email+"\n"+password : "");
            fileWriter.close();

            result.close();
            statement.close();
            MysqlConnect.disconnect();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        } finally {
            MysqlConnect.disconnect();
        }

        return "success";
    }
}
