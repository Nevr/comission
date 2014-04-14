package by.leshiy.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import by.leshiy.service.Service;

public class InvocationHandlerImpl implements InvocationHandler {
    private Service service;

    public InvocationHandlerImpl(Service service) {
	this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
	try {
	    Object result = method.invoke(service, arguments);
	    service.getManager().transactionCommit();
	    return result;
	} catch (Exception e) {
	    try {
		service.getManager().transactionRollback();
	    } catch (Exception e1) {
	    }
	    throw e;
	} finally {
	    service.getManager().transactionClose();
	}
    }
}