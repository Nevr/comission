package by.leshiy.service.Impl;

import by.leshiy.dao.DaoManager;
import by.leshiy.service.Service;

public class ServiceImpl implements Service {
    private DaoManager manager = null;

    public ServiceImpl(DaoManager factory) {
	this.manager = factory;
    }

    @Override
    public DaoManager getManager() {
	return manager;
    }

}
