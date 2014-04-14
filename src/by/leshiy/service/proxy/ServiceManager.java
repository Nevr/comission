package by.leshiy.service.proxy;

import by.leshiy.service.Service;


/**
 * @author Pavel Leshchov
 * 
 */
public interface ServiceManager {
    
    /**
     * @param key
     *            interface of getting service
     * @return service class
     * @throws Exception
     */
    public <Type extends Service> Type getService(Class<Type> key) throws Exception;
}
