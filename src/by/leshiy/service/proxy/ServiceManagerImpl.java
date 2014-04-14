package by.leshiy.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.leshiy.dao.DaoManager;
import by.leshiy.dao.Impl.DaoManagerFactory;
import by.leshiy.service.Service;
import by.leshiy.service.ServiceAbiturient;
import by.leshiy.service.ServiceFaculty;
import by.leshiy.service.ServiceSubjects;
import by.leshiy.service.Impl.ServiceAbiturientImpl;
import by.leshiy.service.Impl.ServiceFacultyImpl;
import by.leshiy.service.Impl.ServiceSubjectsImpl;

public class ServiceManagerImpl implements ServiceManager {
    private static Map<Class<? extends Service>, Class<? extends Service>> services = new ConcurrentHashMap<>();

    static {
	services.put(ServiceAbiturient.class, ServiceAbiturientImpl.class);
	services.put(ServiceFaculty.class, ServiceFacultyImpl.class);
	services.put(ServiceSubjects.class, ServiceSubjectsImpl.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Type extends Service> Type getService(Class<Type> key) throws Exception {
	Class<? extends Service> value = services.get(key);
	if (value != null) {
	    try {
		ClassLoader classLoader = value.getClassLoader();
		Class<?>[] interfaces = { key };
		Service service = value.getConstructor(DaoManager.class).newInstance(
			DaoManagerFactory.getDaoManager());
		InvocationHandler handler = new InvocationHandlerImpl(service);
		return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return null;
    }
}
