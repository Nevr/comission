package by.leshiy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.Dao;
import by.leshiy.dao.DaoManager;
import by.leshiy.dao.FacultyDao;
import by.leshiy.dao.SubjectsDao;
import by.leshiy.dao.SubjectsListDao;
import by.leshiy.db.Entity;
import by.leshiy.db.Faculty;
import by.leshiy.db.Subjects;
import by.leshiy.service.ServiceSubjects;

public class ServiceSubjectsImpl extends ServiceImpl implements ServiceSubjects {

    public ServiceSubjectsImpl(DaoManager manager) {
	super(manager);
    }

    private Dao daoSub = getManager().getDao(SubjectsDao.class);
    private Dao daoFac = getManager().getDao(FacultyDao.class);
    private Dao daoSubList = getManager().getDao(SubjectsListDao.class);

    private List<Entity> find(List<Entity> listSubjects) {
	List<String> facId = new ArrayList<>();
	try {
	    for (Entity f : listSubjects) {
		List<Faculty> listFaculty = new ArrayList<>();
		facId = daoSubList.getByField("subject_id", (Integer.toString(f.getId())));
		for (String s : facId) {
		    List<Entity> faculty = daoFac.getById(Integer.parseInt(s));
		    listFaculty.add((Faculty) faculty.get(0));
		}
		((Subjects) f).setFaculty(listFaculty);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return listSubjects;
    }

    @Override
    public List<Entity> findById(int id) {
	return find(daoSub.getById(id));
    }

    @Override
    public List<Entity> findAll() {
	return find(daoSub.getAll());
    }

    @Override
    public void delete(Entity db) {
	daoSub.delete(db);
    }

    @Override
    public void add(Entity db) {
	daoSub.add(db);
    }

}
