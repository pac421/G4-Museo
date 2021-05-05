package dao;

import bean.Artist;
import bean.Role;
import bean.State;
import bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoleDAO extends DAO<Role> {
    @Override
    public ArrayList<Role> findAll() {
        try {
            String sql = "SELECT * FROM ROLE";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<Role> roles = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String label = result.getString("label");
                Role role = new Role(id, label);
                roles.add(role);
            }
            result.close();
            statement.close();

            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Role find(String id) {
        try {
            String sql = "SELECT * FROM ROLE WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String label = result.getString("label");
                Role role = new Role(id, label);

                result.close();
                statement.close();

                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Role create(Role role) {
        try {
            String sql = "INSERT INTO ROLE (id, label) VALUES (?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, role.getId());
            statement.setString(2, role.getLabel());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Role update(Role role) {
        try {
            String sql = "UPDATE ROLE SET label = ? WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, role.getLabel());
            statement.setString(2, role.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Role role) {
        try {
            String sql = "DELETE FROM ROLE WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, role.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
