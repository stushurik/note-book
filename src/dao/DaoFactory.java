package dao;

import java.sql.Connection;
import java.sql.SQLException;


public interface DaoFactory {

    public Connection getConnection() throws SQLException, ClassNotFoundException;

    public GenericDao getDao(Class Dao) throws NoSuchMethodException, Exception;

}
