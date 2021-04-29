package dao;

import bean.Artist;

public class DAOFactory {
    public static DAO<Artist> getArtistDAO(){
        return new ArtistDAO();
    }
}
