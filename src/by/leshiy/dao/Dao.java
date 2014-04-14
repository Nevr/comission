package by.leshiy.dao;

import java.io.Serializable;
import java.util.List;

import by.leshiy.db.Entity;

/**
 * @author Pavel Leshchov
 * 
 */

public interface Dao extends Serializable {

    /**
     * Execute query form ("Select * from " + (Db implements DbInterface)
     * 
     * @return return all data from the table (Db implements DbInterface)
     */
    List<Entity> getAll();

    /**
     * Execute query form ("Select * from " + Db implements DbInterface + " where id=" + id)<br>
     * 
     * @return all data from the table (Db implements DbInterface) by field id
     */
    List<Entity> getById(int id);

    /**
     * "SELECT " + field + " FROM " + Db implements DbInterface + " WHERE " + whereField + " = " +
     * whereValue
     * 
     * @param field
     * @param whereField
     * @param whereValue
     * @return
     */
    List<String> getByField(String field, String id);

    /**
     * Execute query form ("UPDATE " + Db implements DbInterface + " SET " + field + " = " + value +
     * " WHERE " + whereField + " = " + whereValue) <br>
     * 
     */
    void update(Entity db);

    void add(Entity db);

    void delete(Entity db);
}
