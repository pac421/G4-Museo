package dao;

import bean.Artist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtistDAO extends DAO<Artist> {
    @Override
    public ArrayList<Artist> findAll(HashMap<String, String> filters) {
        try {
            String sql = "SELECT * FROM ARTIST";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<Artist> artists = new ArrayList<>();
            while (result.next()) {
                String id = result.getString("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String period = result.getString("period");
                Artist artist = new Artist(id, firstname, lastname, period);
                artists.add(artist);
            }
            result.close();
            statement.close();

            return artists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Artist find(String id) {
        try {
            String sql = "SELECT * FROM ARTIST WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String period = result.getString("period");
                Artist artist = new Artist(id, firstname, lastname, period);

                result.close();
                statement.close();

                return artist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Artist create(Artist artist) {
        try {
            String sql = "INSERT INTO ARTIST (id, firstname, lastname, period) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setString(1, artist.getId());
            statement.setString(2, artist.getFirstname());
            statement.setString(3, artist.getLastname());
            statement.setString(4, artist.getPeriod());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return artist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Artist update(Artist artist) {
        try {
            String sql = "UPDATE ARTIST SET firstname = ?, lastname = ?, period = ? WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, artist.getFirstname());
            statement.setString(2, artist.getLastname());
            statement.setString(3, artist.getPeriod());
            statement.setString(4, artist.getId());
            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                return artist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Artist artist) {
        try {
            String sql = "DELETE FROM ARTIST WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, artist.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
