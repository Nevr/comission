package by.leshiy.dao.Impl;

import by.leshiy.dao.DaoManager;

public class DaoManagerFactory {

    public static DaoManager getDaoManager() {
	return new DaoManagerImpl();
    }

}
