package dao;

import bean.Property;
import bean.PropertyState;
import bean.State;

import java.sql.*;
import java.util.ArrayList;

public class StateDAO extends DAO<State> {
    @Override
    public ArrayList<State> findAll() {
        try {
            String sql = "SELECT * FROM STATE";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<State> states = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String label = result.getString("label");
                State state = new State(id, label);
                states.add(state);
            }
            result.close();
            statement.close();

            return states;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

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

    public ArrayList<PropertyState> findAllProperty(State state) {
        try {
            String sql = "SELECT * FROM PROPERTY_STATE PS INNER JOIN PROPERTY P ON PS.property_id = P.id WHERE PS.state_id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, state.getId());
            ResultSet result = statement.executeQuery();

            ArrayList<PropertyState> propertyStates = new ArrayList<PropertyState>();

            while (result.next()) {
                Date start = result.getDate("start");
                Date end = result.getDate("end");
                String comment = result.getString("comment");
                String property_id = result.getString("property_id");

                DAO<Property> propertyDAO = new DAOFactory().getPropertyDAO();
                Property property_obj = propertyDAO.find(property_id);

                PropertyState propertyState = new PropertyState(property_obj, state, start, end, comment);
                propertyStates.add(propertyState);
            }

            result.close();
            statement.close();

            return propertyStates;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
