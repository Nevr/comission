package by.leshiy.dao;

public interface DaoManager {

    public Dao getDao(Class<? extends Dao> key);

    public void transactionCommit();

    public void transactionRollback();

    public void transactionClose();

}
