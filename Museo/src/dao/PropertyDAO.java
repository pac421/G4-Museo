package dao;

import bean.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyDAO extends DAO<Property> {

    @Override
    public ArrayList<Property> findAll() {
        return null;
    }

    @Override
    public Property find(String id) {
        try {
            String sql = "SELECT * FROM PROPERTY" +
                    " INNER JOIN WORK ON WORK.id = PROPERTY.work_id" +
                    " INNER JOIN COLLECTION ON WORK.collection_id = COLLECTION.id" +
                    " INNER JOIN CATEGORY ON WORK.category_id = CATEGORY.id" +
                    " LEFT JOIN USER ON WORK.deleted_by_id = USER.id" +
                    " WHERE PROPERTY.work_id = ?";

            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Date ownedAt = result.getDate("PROPERTY.owned_at");
                String ownedFrom = result.getString("PROPERTY.owned_from");
                Double price = result.getDouble("PROPERTY.price");
                String label = result.getString("WORK.label");
                String description = result.getString("WORK.description");
                String period = result.getString("WORK.period");
                Double height = result.getDouble("WORK.height");
                Double width = result.getDouble("WORK.width");
                Double depth = result.getDouble("WORK.depth");
                Double weight = result.getDouble("WORK.weight");
                Date deletedAt = result.getDate("WORK.deleted_at");
                String userId = result.getString("WORK.deleted_by_id");
                String collectionId = result.getString("collection_id");
                String collectionLabel = result.getString("COLLECTION.label");
                String collectionPeriod = result.getString("COLLECTION.period");
                String categoryId = result.getString("category_id");
                String categoryLabel = result.getString("CATEGORY.label");

                User deletedBy = new User();
                if (userId != null) {
                    deletedBy.setFirstname(result.getString("USER.firstname"));
                    deletedBy.setLastname(result.getString("USER.lastname"));
                    deletedBy.setEmail(result.getString("USER.email"));
                }
                Collection collection = new Collection(collectionId, collectionLabel, collectionPeriod);
                Category category = new Category(categoryId, categoryLabel);

                Property property = new Property(id, label, description, period, height, width, depth, weight, deletedAt, deletedBy, collection, category, ownedAt, ownedFrom, price);

                result.close();
                statement.close();

                return property;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Property create(Property property) {
        try {
            // First create work
            String workSql = "INSERT INTO WORK (id, label, description, period, height, width, depth, weight, collection_id, category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement workStatement = connect.prepareStatement(workSql);

            workStatement.setString(1, property.getId());
            workStatement.setString(2, property.getLabel());
            workStatement.setString(3, property.getDescription());
            workStatement.setString(4, property.getPeriod());
            workStatement.setDouble(5, property.getHeight());
            workStatement.setDouble(6, property.getWidth());
            workStatement.setDouble(7, property.getDepth());
            workStatement.setDouble(8, property.getWeight());
            workStatement.setString(9, property.getCollection().getId());
            workStatement.setString(10, property.getCategory().getId());
            int workRows = workStatement.executeUpdate();
            workStatement.close();

            if (workRows > 0) {
                // Then create property
                String propertySql = "INSERT INTO PROPERTY (work_id, owned_at, owned_from, price) VALUES (?, ?, ?, ?)";
                PreparedStatement propertyStatement = connect.prepareStatement(propertySql);

                propertyStatement.setString(1, property.getId());
                propertyStatement.setDate(2, property.getOwnedAt());
                propertyStatement.setString(3, property.getOwnedFrom());
                propertyStatement.setDouble(4, property.getPrice());

                int propertyRows = propertyStatement.executeUpdate();
                propertyStatement.close();

                if (propertyRows > 0) {
                    return property;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Property update(Property property) {
        try {
            String workSql = "UPDATE WORK SET label = ?, description = ?, period = ?, height = ?, width = ?, depth = ?, weight = ?, collection_id = ?, category_id = ?, deleted_at = ?, deleted_by_id = ?" +
                    " WHERE id = ?";
            PreparedStatement workStatement = connect.prepareStatement(workSql);

            workStatement.setString(1, property.getLabel());
            workStatement.setString(2, property.getDescription());
            workStatement.setString(3, property.getPeriod());
            workStatement.setDouble(4, property.getHeight());
            workStatement.setDouble(5, property.getWidth());
            workStatement.setDouble(6, property.getDepth());
            workStatement.setDouble(7, property.getWeight());
            workStatement.setString(8, property.getCollection().getId());
            workStatement.setString(9, property.getCategory().getId());
            workStatement.setDate(10, property.getDeletedAt());
            workStatement.setString(11, property.getDeletedBy().getId());
            workStatement.setString(12, property.getId());
            int workRows = workStatement.executeUpdate();
            workStatement.close();

            if (workRows > 0) {
                String propertySql = "UPDATE PROPERTY SET owned_at = ?, owned_from = ?, price = ?" +
                        " WHERE work_id = ?";
                PreparedStatement propertyStatement = connect.prepareStatement(propertySql);

                propertyStatement.setDate(1, property.getOwnedAt());
                propertyStatement.setString(2, property.getOwnedFrom());
                propertyStatement.setDouble(3, property.getPrice());
                propertyStatement.setString(4, property.getId());
                int propertyRows = propertyStatement.executeUpdate();
                propertyStatement.close();

                if (propertyRows > 0) {
                    return property;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Property property) {

    }
}
