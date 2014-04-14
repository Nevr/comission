package by.leshiy.service;

import java.util.List;

import by.leshiy.db.Entity;

public interface ServiceSubjects extends Service {

    List<Entity> findById(int id);

    List<Entity> findAll();

    void delete(Entity db);

    void add(Entity db);

}
