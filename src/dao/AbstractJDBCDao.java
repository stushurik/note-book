package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public abstract class AbstractJDBCDao<T, PK> implements GenericDao<T, PK> {

    private Connection connection;

    public abstract String getSelectQuery();

    public String getSelectQuery(Map searchParams) {

        String sql = "SELECT * FROM USERS \n";

        int i = 1;
        String conditions = "WHERE";

        for(Object key: searchParams.keySet()) {
            String condition = "";

            String[] value = (String[]) searchParams.get(key);

            if (value[0].length() != 0) {

                if( ( key.equals("age") ) || ( key.equals("id") ) ){
                    condition = " " + key.toString().toUpperCase() + "=" + value[0].toUpperCase();
                } else {
                    condition = " upper(" + key.toString().toUpperCase() + ") LIKE '%" + value[0].toUpperCase() + "%'";
                }

                if (i == 1) {
                    conditions = conditions + condition;
                    i++;
                } else {
                    conditions = conditions + " AND"+ condition;
                }

            }
        }
        if (conditions.equals("WHERE")){
            return sql;
        } else {
            return sql + conditions;
        }
    }

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws Exception;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws Exception;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws Exception;

    protected void prepareStatementForSearch(PreparedStatement statement, String field) throws Exception {
        try {
            statement.setString(1, field);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public void create(T object) throws SQLException {
        try {
            if (object.getClass().getMethod("getId").invoke(object) != null) {
                throw new SQLException("Object is already persist.");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);

            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public T getByPK(Integer key) throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new SQLException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public List<T> search(Map searchParams) throws SQLException {

        List<T> list;
        String sql = getSelectQuery(searchParams);
        System.out.println(sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return list;

    }

    @Override
    public void update(T object) throws SQLException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);

            System.out.println(statement);

            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(T object) throws SQLException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getClass().getMethod("getId").invoke(object));
            } catch (Exception e) {
                throw new Exception(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }
}