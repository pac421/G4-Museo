package dao;

import bean.Role;
import bean.State;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDAO extends DAO<State> {
    @Override
    public State find(String id) {
        try {
            String sql = "SELECT * FROM STATE WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String label = result.getString("label");
                State state = new State(id, label);

                result.close();
                statement.close();

                return state;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public State create(State state) {
        try {
            String sql = "INSERT INTO STATE (id, label) VALUES (?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, state.getId());
            statement.setString(2, state.getLabel());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return state;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public State update(State state) {
        try {
            String sql = "UPDATE STATE SET label = ? WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, state.getLabel());
            statement.setString(2, state.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return state;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(State state) {
        try {
            String sql = "DELETE FROM STATE WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, state.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
