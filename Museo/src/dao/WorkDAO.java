package dao;

import bean.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class WorkDAO extends DAO<Work>{

    @Override
    public ArrayList<Work> findAll(HashMap<String, String> filters) {
        try {
            String sql = "SELECT * FROM WORK W" +
                    " INNER JOIN COLLECTION CO ON W.collection_id = CO.id" +
                    " INNER JOIN CATEGORY CA ON W.category_id = CA.id" +
                    " LEFT JOIN USER U ON W.deleted_by_id = U.id ";
            sql = addFilters(sql, filters);
            sql += filters.size()>0 ? " WHERE W.deleted_at IS NULL" : " WHERE W.deleted_at IS NULL";
            System.out.println(sql);
            PreparedStatement statement = connect.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            ArrayList<Work> works = new ArrayList<Work>();

            while (result.next()) {
                String id = result.getString("W.id");
                String label = result.getString("W.label");
                String description = result.getString("W.description");
                String period = result.getString("W.period");
                Double height = result.getDouble("W.height");
                Double width = result.getDouble("W.width");
                Double depth = result.getDouble("W.depth");
                Double weight = result.getDouble("W.weight");
                Date deletedAt = result.getDate("W.deleted_at");
                String userId = result.getString("W.deleted_by_id");
                String collectionId = result.getString("W.collection_id");
                String collectionLabel = result.getString("CO.label");
                String collectionPeriod = result.getString("CO.period");
                String categoryId = result.getString("W.category_id");
                String categoryLabel = result.getString("CA.label");

                User deletedBy = new User();
                if (userId != null) {
                    deletedBy.setFirstname(result.getString("U.firstname"));
                    deletedBy.setLastname(result.getString("U.lastname"));
                    deletedBy.setEmail(result.getString("U.email"));
                }
                Collection collection = new Collection(collectionId, collectionLabel, collectionPeriod);
                Category category = new Category(categoryId, categoryLabel);

                Work work = new Work(id, label, description, period, height, width, depth, weight, deletedAt, deletedBy, collection, category);
                works.add(work);
            }
            result.close();
            statement.close();

            return works;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Work find(String id) {
        try {
            String sql = "SELECT * FROM WORK" +
                    " INNER JOIN COLLECTION ON WORK.collection_id = COLLECTION.id" +
                    " INNER JOIN CATEGORY ON WORK.category_id = CATEGORY.id" +
                    " LEFT JOIN USER ON WORK.deleted_by_id = USER.id" +
                    " WHERE WORK.id = ?";

            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String label = result.getString("WORK.label");
                String description = result.getString("WORK.description");
                String period = result.getString("WORK.period");
                Double height = result.getDouble("WORK.height");
                Double width = result.getDouble("WORK.width");
                Double depth = result.getDouble("WORK.depth");
                Double weight = result.getDouble("WORK.weight");
                Date deletedAt = result.getDate("WORK.deleted_at");
                String userId = result.getString("WORK.deleted_by_id");
                String collectionId = result.getString("WORK.collection_id");
                String collectionLabel = result.getString("COLLECTION.label");
                String collectionPeriod = result.getString("COLLECTION.period");
                String categoryId = result.getString("WORK.category_id");
                String categoryLabel = result.getString("CATEGORY.label");

                User deletedBy = new User();
                if (userId != null) {
                    deletedBy.setFirstname(result.getString("USER.firstname"));
                    deletedBy.setLastname(result.getString("USER.lastname"));
                    deletedBy.setEmail(result.getString("USER.email"));
                }
                Collection collection = new Collection(collectionId, collectionLabel, collectionPeriod);
                Category category = new Category(categoryId, categoryLabel);

                Work work = new Work(id, label, description, period, height, width, depth, weight, deletedAt, deletedBy, collection, category);

                result.close();
                statement.close();

                return work;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Work create(Work work) {
        try {
            String sql = "INSERT INTO WORK (id, label, description, period, height, width, depth, weight, collection_id, category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, work.getId());
            statement.setString(2, work.getLabel());
            statement.setString(3, work.getDescription());
            statement.setString(4, work.getPeriod());
            statement.setDouble(5, work.getHeight());
            statement.setDouble(6, work.getWidth());
            statement.setDouble(7, work.getDepth());
            statement.setDouble(8, work.getWeight());
            statement.setString(9, work.getCollection().getId());
            statement.setString(10, work.getCategory().getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return work;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Work update(Work work) {
        try {
            String sql = "UPDATE WORK SET label = ?, description = ?, period = ?, height = ?, width = ?, depth = ?, weight = ?, collection_id = ?, category_id = ?, deleted_at = ?, deleted_by_id = ?" +
                    " WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, work.getLabel());
            statement.setString(2, work.getDescription());
            statement.setString(3, work.getPeriod());
            statement.setDouble(4, work.getHeight());
            statement.setDouble(5, work.getWidth());
            statement.setDouble(6, work.getDepth());
            statement.setDouble(7, work.getWeight());
            statement.setString(8, work.getCollection().getId());
            statement.setString(9, work.getCategory().getId());
            statement.setDate(10, work.getDeletedAt());
            statement.setString(11, work.getDeletedBy().getId());
            statement.setString(12, work.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return work;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Work work) {

    }
}
