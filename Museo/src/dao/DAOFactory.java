package dao;

import bean.Artist;
import bean.Category;
import bean.Collection;

public class DAOFactory {
    public static DAO<Artist> getArtistDAO(){
        return new ArtistDAO();
    }

    public static DAO<Category> getCategoryDAO(){
        return new CategoryDAO();
    }

    public static DAO<Collection> getCollectionDAO(){
        return new CollectionDAO();
    }
}

