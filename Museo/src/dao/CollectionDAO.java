package dao;

import bean.Collection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CollectionDAO extends DAO<Collection> {
    @Override
    public ArrayList<Collection> findAll() {
        try {
            String sql = "SELECT * FROM COLLECTION";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<Collection> collections = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String label = result.getString("label");
                String period = result.getString("period");
                Collection collection = new Collection(id, label, period);
                collections.add(collection);
            }
            result.close();
            statement.close();

            return collections;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection find(String id) {
        try {
            String sql = "SELECT * FROM Collection WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String label = result.getString("label");
                String period = result.getString("period");
                Collection collection = new Collection(id, label, period);

                result.close();
                statement.close();

                return collection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection create(Collection collection) {
        try {
            String sql = "INSERT INTO Collection (id, label, period) VALUES (?, ?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, collection.getId());
            statement.setString(2, collection.getLabel());
            statement.setString(3, collection.getPeriod());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return collection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection update(Collection collection) {
        try {
            String sql = "UPDATE Collection SET label = ?, period = ? WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, collection.getLabel());
            statement.setString(2, collection.getPeriod());
            statement.setString(3, collection.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return collection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Collection collection) {
        try {
            String sql = "DELETE FROM Collection WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, collection.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
