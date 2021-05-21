package dao;

import bean.Role;
import bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDAO extends DAO<User> {
    @Override
    public ArrayList<User> findAll(HashMap<String, String> filters) {
        try {
            String sql = "SELECT * FROM USER" +
                    " LEFT JOIN ROLE ON USER.role_id = ROLE.id";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<User> users = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String password = result.getString("password");
                String roleId = result.getString("ROLE.id");
                String roleLabel = result.getString("ROLE.label");

                Role role = new Role(roleId, roleLabel);
                User user = new User(id, firstname, lastname, email, password, role);
                users.add(user);
            }
            result.close();
            statement.close();

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User find(String id) {
        try {
            String sql = "SELECT * FROM USER " +
                    "LEFT JOIN ROLE ON USER.role_id = ROLE.id " +
                    "WHERE USER.id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String password = result.getString("password");
                String roleId = result.getString("ROLE.id");
                String roleLabel = result.getString("ROLE.label");

                Role role = new Role(roleId, roleLabel);
                User user = new User(id, firstname, lastname, email, password, role);

                result.close();
                statement.close();

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User create(User user) {
        try {
            String sql = "INSERT INTO USER (id, firstname, lastname, email, password, role_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, user.getId());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole().getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User update(User user) {
        try {
            String sql = "UPDATE USER SET firstname = ?, lastname = ?, email = ?, password = ?, role_id = ? WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().getId());
            statement.setString(6, user.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(User user) {
        try {
            String sql = "DELETE FROM USER WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
