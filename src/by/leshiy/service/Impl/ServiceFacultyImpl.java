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
import by.leshiy.service.ServiceFaculty;

public class ServiceFacultyImpl extends ServiceImpl implements ServiceFaculty {

    public ServiceFacultyImpl(DaoManager manager) {
	super(manager);
    }

    private Dao daoFac = getManager().getDao((Class<? extends Dao>) FacultyDao.class);
    private Dao daoSub = getManager().getDao((Class<? extends Dao>) SubjectsDao.class);
    private Dao daoSubList = getManager().getDao((Class<? extends Dao>) SubjectsListDao.class);

    private List<Entity> find(List<Entity> listFaculty) {
	List<String> subjId = new ArrayList<>();
	try {
	    for (Entity f : listFaculty) {
		List<Subjects> listSubjects = new ArrayList<Subjects>();
		int idF = f.getId();
		subjId = daoSubList.getByField("faculty_id", (Integer.toString(idF)));
		if (subjId.size() > 0) {
		    for (String s : subjId) {
			List<Entity> subjects = daoSub.getById(Integer.parseInt(s));
			listSubjects.add((Subjects) subjects.get(0));
		    }
		    ((Faculty) f).setSubjects(listSubjects);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return listFaculty;
    }

    @Override
    public List<Entity> findById(int id) {
	return find(daoFac.getById(id));
    }

    @Override
    public List<Entity> findAll() {
	return find(daoFac.getAll());
    }

    @Override
    public void delete(Entity db) {
	daoFac.delete(db);
    }

    @Override
    public void add(Entity db) {
	daoFac.add(db);
    }

}
