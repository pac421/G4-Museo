package dao;

import bean.Role;
import bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO extends DAO<Role> {
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
