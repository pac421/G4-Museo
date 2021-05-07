package dao;

import bean.*;

import java.sql.*;
import java.util.ArrayList;

public class PropertyStateDAO extends DAO<PropertyState> {
    @Override
    public ArrayList<PropertyState> findAll() {
        try {
            String sql = "SELECT * FROM PROPERTY_STATE";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<PropertyState> propertiesStates = new ArrayList<PropertyState>();

            DAO<Property> propertyDAO = new DAOFactory().getPropertyDAO();
            DAO<State> stateDAO = new DAOFactory().getStateDAO();

            while (result.next()) {
                String id = result.getString("id");
                Date start = result.getDate("start");
                Date end = result.getDate("end");
                String comment = result.getString("comment");
                Property property = propertyDAO.find(result.getString("property_id"));
                State state = stateDAO.find(result.getString("state_id"));

                if (property != null && state != null) {
                    PropertyState propertyState = new PropertyState(id, property, state, start, end, comment);
                    propertiesStates.add(propertyState);
                }
            }

            result.close();
            statement.close();

            return propertiesStates;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PropertyState find(String id) {
        try {
            String sql = "SELECT * FROM PROPERTY_STATE WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                DAO<Property> propertyDAO = new DAOFactory().getPropertyDAO();
                DAO<State> stateDAO = new DAOFactory().getStateDAO();

                Date start = result.getDate("start");
                Date end = result.getDate("end");
                String comment = result.getString("comment");
                Property property = propertyDAO.find(result.getString("property_id"));
                State state = stateDAO.find(result.getString("state_id"));

                if (property != null && state != null) {
                    PropertyState propertyState = new PropertyState(id, property, state, start, end, comment);

                    return propertyState;
                }

                result.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PropertyState create(PropertyState propertyState) {
        try {
            String sql = "INSERT INTO PROPERTY_STATE (id, property_id, state_id, start, end, comment) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, propertyState.getId());
            statement.setString(2, propertyState.getProperty().getId());
            statement.setString(3, propertyState.getState().getId());
            statement.setDate(4, propertyState.getStart());
            statement.setDate(5, propertyState.getEnd());
            statement.setString(6, propertyState.getComment());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return propertyState;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PropertyState update(PropertyState propertyState) {
        try {
            String sql = "UPDATE PROPERTY_STATE SET property_id = ?, state_id = ?, start = ?, end = ?, comment = ? WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, propertyState.getProperty().getId());
            statement.setString(2, propertyState.getState().getId());
            statement.setDate(3, propertyState.getStart());
            statement.setDate(4, propertyState.getEnd());
            statement.setString(5, propertyState.getComment());
            statement.setString(6, propertyState.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return propertyState;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(PropertyState propertyState) {
        try {
            String sql = "DELETE FROM PROPERTY_STATE WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, propertyState.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
