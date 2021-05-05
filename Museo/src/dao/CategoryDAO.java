package dao;

import bean.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDAO extends DAO<Category> {
    @Override
    public ArrayList<Category> findAll() {
        try {
            String sql = "SELECT * FROM CATEGORY";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<Category> categories = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String label = result.getString("label");
                Category category = new Category(id, label);
                categories.add(category);
            }
            result.close();
            statement.close();

            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Category find(String id) {
        try {
            String sql = "SELECT * FROM CATEGORY WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String label = result.getString("label");
                Category category = new Category(id, label);

                result.close();
                statement.close();

                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Category create(Category category) {
        try {
            String sql = "INSERT INTO CATEGORY (id, label) VALUES (?,?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, category.getId());
            statement.setString(2, category.getLabel());

            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Category update(Category category) {
        try {
            String sql = "UPDATE CATEGORY SET label = ? WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, category.getLabel());
            statement.setString(2, category.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Category category) {
        try {
            String sql = "DELETE FROM CATEGORY WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, category.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
