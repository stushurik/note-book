package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface GenericDao<T, PK> {

    public void create(T object) throws SQLException;

    public T getByPK(Integer key) throws SQLException;

    public void update(T object) throws SQLException;

    public void delete(T object) throws SQLException;

    public List<T> getAll() throws SQLException;

    public List<T> search(Map searchParams) throws SQLException;

}
