package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DAO<T> {
    public Connection connect = MysqlConnect.connect();

    /**
     * Get all objects from DB*
     */
    public abstract ArrayList<T> findAll(HashMap<String, String> filters);

    /**
     * Get one object by ID from DB
     */
    public abstract T find(String id);

    /**
     * Create in DB
     */
    public abstract T create(T obj);

    /**
     * Update in DB
     */
    public abstract T update(T obj);

    /**
     * Delete from DB
     */
    public abstract void delete(T obj);

    public String addFilters(String sql, HashMap<String, String> filters) {
        int count_filters = filters.size();
        if(count_filters > 0) {
            sql += " WHERE ";
            int i = 1;
            StringBuilder sqlBuilder = new StringBuilder(sql);
            for (Map.Entry<String, String> filter : filters.entrySet()) {
                sqlBuilder.append(filter.getKey()+"='"+filter.getValue()+"'");
                if(i < count_filters) {
                    sqlBuilder.append(", ");
                }
                i++;
            }
            sql = sqlBuilder.toString();
        }
        return sql;
    }
}
