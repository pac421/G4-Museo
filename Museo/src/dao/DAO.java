package dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
    public Connection connect = MysqlConnect.connect();

    /**
     * Get all objects from DB
     * @return
     */
    public abstract ArrayList<T> findAll();

    /**
     * Get one object by ID from DB
     * @param id
     * @return
     */
    public abstract T find(String id);

    /**
     * Create in DB
     * @param obj
     */
    public abstract T create(T obj);

    /**
     * Update in DB
     * @param obj
     */
    public abstract T update(T obj);

    /**
     * Delete from DB
     * @param obj
     */
    public abstract void delete(T obj);

    public String addFilters(String sql) {
        return sql;
    }
}
