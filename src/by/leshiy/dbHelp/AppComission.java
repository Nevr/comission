package by.leshiy.dbHelp;

import java.util.List;

import by.leshiy.db.Abiturient;
import by.leshiy.db.Entity;
import by.leshiy.db.Faculty;
import by.leshiy.db.Subjects;
import by.leshiy.service.ServiceAbiturient;
import by.leshiy.service.ServiceFaculty;
import by.leshiy.service.ServiceSubjects;
import by.leshiy.service.proxy.ServiceManager;
import by.leshiy.service.proxy.ServiceManagerImpl;

public class AppComission {

    static List<Entity> abit;
    static List<Entity> faculty;
    static List<Entity> subjects;
    static ServiceManager manager = new ServiceManagerImpl();

    public static void main(String[] args) {
	// new AppComission().deleteAbit();
	System.out.println("--- Вывод из метода selectAbiturient ---");
	selectAbiturient();
	System.out.println("------------------------------------------------------");
	System.out.println("--- Вывод из метода selectFaculty ---");
	selectFaculty();
	System.out.println("------------------------------------------------------");
	System.out.println("--- Вывод из метода selectSubjects ---");
	selectSubjects();
	ConnectionPool.getConnPool().closeAll();
    }

    public void deleteAbit() {
	try {
	    Entity ab = new Subjects();
	    ab.setId(2);
	    manager.getService(ServiceSubjects.class).delete(ab);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void selectSubjects() {
	try {
	    subjects = manager.getService(ServiceSubjects.class).findAll();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	for (Entity s : subjects) {
	    Subjects sub = (Subjects) s;
	    System.out.print("Название предмета  ");
	    System.out.println(sub.getName() + " ");
	    for (Entity fac : sub.getFaculty()) {
		System.out.println(((Faculty) fac).getName());
	    }
	    System.out.println("-------------------------------------------");
	}
    }

    public static void selectAbiturient() {
	try {
	    abit = manager.getService(ServiceAbiturient.class).findAll();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	for (Entity a : abit) {
	    Abiturient ab = (Abiturient) a;
	    System.out.print(ab.getName() + " ");
	    System.out.print(ab.getPatronomic() + " ");
	    System.out.println(ab.getSurname());
	    System.out.println(ab.getFaculty().getName());
	}
    }

    public static void selectFaculty() {
	try {
	    faculty = manager.getService(ServiceFaculty.class).findAll();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	for (Entity f : faculty) {
	    Faculty fac = (Faculty) f;
	    System.out.print("ID факультета  ");
	    System.out.println(fac.getId() + " ");
	    System.out.print("Название факультета  ");
	    System.out.println(fac.getName() + " ");
	    System.out.print("План набора на факультет  ");
	    System.out.println(fac.getRecruitPlan() + " ");
	    System.out.print("Проходной бал составил  ");
	    System.out.println(fac.getCheckRating() + " ");
	    System.out.println("Экзаменационные предметы для поступления на факультет  ");
	    for (Entity subject : fac.getSubjects()) {
		System.out.println(((Subjects) subject).getName());
	    }
	    System.out.println("-------------------------------------------");
	}
    }
}
