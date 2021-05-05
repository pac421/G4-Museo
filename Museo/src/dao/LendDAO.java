package dao;

import bean.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LendDAO extends DAO<Lend> {
    @Override
    public ArrayList<Lend> findAll() {
        return null;
    }

    @Override
    public Lend find(String id) {
        try {
            String sql = "SELECT * FROM LEND" +
                    " INNER JOIN WORK ON WORK.id = LEND.work_id" +
                    " INNER JOIN COLLECTION ON WORK.collection_id = COLLECTION.id" +
                    " INNER JOIN CATEGORY ON WORK.category_id = CATEGORY.id" +
                    " LEFT JOIN USER ON WORK.deleted_by_id = USER.id" +
                    " WHERE LEND.work_id = ?";

            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Date start = result.getDate("LEND.start");
                Date end = result.getDate("LEND.end");
                String lender = result.getString("LEND.lender");
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

                Lend lend = new Lend(id, label, description, period, height, width, depth, weight, deletedAt, deletedBy, collection, category, start, end, lender);

                result.close();
                statement.close();

                return lend;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Lend create(Lend lend) {
        try {
            // First create work
            String workSql = "INSERT INTO WORK (id, label, description, period, height, width, depth, weight, collection_id, category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement workStatement = connect.prepareStatement(workSql);

            workStatement.setString(1, lend.getId());
            workStatement.setString(2, lend.getLabel());
            workStatement.setString(3, lend.getDescription());
            workStatement.setString(4, lend.getPeriod());
            workStatement.setDouble(5, lend.getHeight());
            workStatement.setDouble(6, lend.getWidth());
            workStatement.setDouble(7, lend.getDepth());
            workStatement.setDouble(8, lend.getWeight());
            workStatement.setString(9, lend.getCollection().getId());
            workStatement.setString(10, lend.getCategory().getId());
            int workRows = workStatement.executeUpdate();
            workStatement.close();

            if (workRows > 0) {
                // Then create lend
                String propertySql = "INSERT INTO LEND (work_id, start, end, lender) VALUES (?, ?, ?, ?)";
                PreparedStatement lendStatement = connect.prepareStatement(propertySql);

                lendStatement.setString(1, lend.getId());
                lendStatement.setDate(2, lend.getStart());
                lendStatement.setDate(3, lend.getEnd());
                lendStatement.setString(4, lend.getLender());

                int lendRows = lendStatement.executeUpdate();
                lendStatement.close();

                if (lendRows > 0) {
                    return lend;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Lend update(Lend lend) {
        try {
            String workSql = "UPDATE WORK SET label = ?, description = ?, period = ?, height = ?, width = ?, depth = ?, weight = ?, collection_id = ?, category_id = ?, deleted_at = ?, deleted_by_id = ?" +
                    " WHERE id = ?";
            PreparedStatement workStatement = connect.prepareStatement(workSql);

            workStatement.setString(1, lend.getLabel());
            workStatement.setString(2, lend.getDescription());
            workStatement.setString(3, lend.getPeriod());
            workStatement.setDouble(4, lend.getHeight());
            workStatement.setDouble(5, lend.getWidth());
            workStatement.setDouble(6, lend.getDepth());
            workStatement.setDouble(7, lend.getWeight());
            workStatement.setString(8, lend.getCollection().getId());
            workStatement.setString(9, lend.getCategory().getId());
            workStatement.setDate(10, lend.getDeletedAt());
            workStatement.setString(11, lend.getDeletedBy().getId());
            workStatement.setString(12, lend.getId());
            int workRows = workStatement.executeUpdate();
            workStatement.close();

            if (workRows > 0) {
                String propertySql = "UPDATE LEND SET start = ?, end = ?, lender = ?" +
                        " WHERE work_id = ?";
                PreparedStatement lendStatement = connect.prepareStatement(propertySql);

                lendStatement.setDate(1, lend.getStart());
                lendStatement.setDate(2, lend.getEnd());
                lendStatement.setString(3, lend.getLender());
                lendStatement.setString(4, lend.getId());
                int lendRows = lendStatement.executeUpdate();
                lendStatement.close();

                if (lendRows > 0) {
                    return lend;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Lend obj) {

    }
}
