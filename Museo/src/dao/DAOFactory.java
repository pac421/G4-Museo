package dao;

import bean.*;

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


    public static DAO<User> getUserDAO(){
        return new UserDAO();
    }

    public static DAO<Role> getRoleDAO(){
        return new RoleDAO();
    }

    public static DAO<State> getStateDAO(){
        return new StateDAO();
    }
}

