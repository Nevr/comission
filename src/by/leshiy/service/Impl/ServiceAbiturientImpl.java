package by.leshiy.service.Impl;

import java.util.List;

import by.leshiy.dao.AbiturientDao;
import by.leshiy.dao.Dao;
import by.leshiy.dao.DaoManager;
import by.leshiy.dao.FacultyDao;
import by.leshiy.db.Abiturient;
import by.leshiy.db.Entity;
import by.leshiy.db.Faculty;
import by.leshiy.service.ServiceAbiturient;

public class ServiceAbiturientImpl extends ServiceImpl implements ServiceAbiturient {

    public ServiceAbiturientImpl(DaoManager manager) {
	super(manager);
    }

    private Dao daoAb = getManager().getDao(AbiturientDao.class);
    private Dao daoFac = getManager().getDao(FacultyDao.class);

    @Override
    public List<Entity> findById(int id) {
	return find(daoAb.getById(id));
    }

    @Override
    public List<Entity> findAll() {
	return find(daoAb.getAll());
    }

    private List<Entity> find(List<Entity> listAbit) {
	try {
	    if (listAbit != null) {
		for (Entity d : listAbit) {
		    int id = ((Abiturient) d).getFaculty().getId();
		    ((Abiturient) d).setFaculty((Faculty) daoFac.getById(id).get(0));
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return listAbit;
    }

    @Override
    public void delete(Entity db) {
	daoAb.delete(db);
    }

    @Override
    public void add(Entity db) {
	daoAb.add(db);
    }

}
