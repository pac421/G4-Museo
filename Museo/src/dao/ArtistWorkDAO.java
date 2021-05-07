package dao;

import bean.*;

import java.sql.*;
import java.util.ArrayList;

public class ArtistWorkDAO extends DAO<ArtistWork> {
    @Override
    public ArrayList<ArtistWork> findAll() {
        try {
            String sql = "SELECT * FROM ARTIST_WORK";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<ArtistWork> artistsWorks = new ArrayList<ArtistWork>();

            DAO<Artist> artistDAO = new DAOFactory().getArtistDAO();
            DAO<Work> workDAO = new DAOFactory().getWorkDAO();

            while (result.next()) {
                Artist artist = artistDAO.find(result.getString("artist_id"));
                Work work = workDAO.find(result.getString("work_id"));

                if (artist != null && work != null) {
                    ArtistWork artistWork = new ArtistWork(work, artist);
                    artistsWorks.add(artistWork);
                }
            }

            result.close();
            statement.close();

            return artistsWorks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArtistWork find(String id) {
        return null;
    }

    @Override
    public ArtistWork create(ArtistWork artistWork) {
        try {
            String sql = "INSERT INTO ARTIST_WORK (work_id, artist_id) VALUES (?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, artistWork.getWork().getId());
            statement.setString(2, artistWork.getArtist().getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return artistWork;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArtistWork update(ArtistWork obj) {
        return null;
    }

    @Override
    public void delete(ArtistWork artistWork) {
        try {
            String sql = "DELETE FROM ARTIST_WORK WHERE work_id = ? AND artist_id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, artistWork.getWork().getId());
            statement.setString(2, artistWork.getArtist().getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
