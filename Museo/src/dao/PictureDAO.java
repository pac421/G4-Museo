package dao;

import bean.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PictureDAO extends DAO<Picture> {
    @Override
    public ArrayList<Picture> findAll(HashMap<String, String> filters) {
        try {
            String sql = "SELECT * FROM PICTURE";
            sql = addFilters(sql, filters);

            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<Picture> pictures = new ArrayList<Picture>();

            DAO<Work> workDAO = new DAOFactory().getWorkDAO();

            while (result.next()) {
                Work work = workDAO.find(result.getString("work_id"));

                if (work != null) {
                    Picture picture = new Picture(result.getString("id"), result.getString("extension"), work);
                    pictures.add(picture);
                }
            }

            result.close();
            statement.close();

            return pictures;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Picture find(String id) {
        return null;
    }

    @Override
    public Picture create(Picture picture) {
        return null;
    }

    @Override
    public Picture update(Picture picture) {
        return null;
    }

    @Override
    public void delete(Picture obj) {}
}
