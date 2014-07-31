package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractJDBCDao<User, Integer> {

    private class PersistUser extends User {
        public void setId(Integer id) {
            super.setId(id);
        }

        public void setLastName(String lastName){
            super.setLastName(lastName);
        }

        public void setFirsName(String firstName){
            super.setFirstName(firstName);
        }

        public void setAge(Integer age){
            super.setAge(age);
        }

        public void setPhone(String phone){
            super.setPhone(phone);
        }

    }

    @Override
    public String getSelectQuery() {

        return "SELECT * FROM USERS";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO USERS (FIRSTNAME, LASTNAME, AGE, SEX, PHONE) \n" +
                "VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE USERS SET FIRSTNAME=?, LASTNAME=?, AGE=?, SEX=?, PHONE=? \n" +
                "WHERE id=?";
    }

    @Override
    public String getDeleteQuery() {

        return "DELETE FROM USERS WHERE id=?";
    }

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public void create(User object) {
        try {
            super.create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistUser user = new PersistUser();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex").toCharArray()[0]);
                user.setPhone(rs.getString("phone"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return result;
    }

    protected void prepareStatement(PreparedStatement statement, User object, Boolean update) throws SQLException {
        try {
            if (object.getFirstName() != null)  statement.setString(1,object.getFirstName());
            else  statement.setNull(1, java.sql.Types.VARCHAR);

            if (object.getLastName() != null)  statement.setString(2,object.getLastName());
            else  statement.setNull(2, java.sql.Types.VARCHAR);

            if (object.getAge() != null)  statement.setInt(3,object.getAge());
            else  statement.setNull(3, java.sql.Types.INTEGER);

            if (object.getSex() != null)  statement.setString(4,object.getSex().toString());
            else  statement.setNull(4, java.sql.Types.VARCHAR);

            if (object.getPhone() != null)  statement.setString(5,object.getPhone());
            else  statement.setNull(5, java.sql.Types.VARCHAR);

            if (update) {

                if (object.getId() != null) statement.setInt(6, object.getId());
                else statement.setNull(6, java.sql.Types.INTEGER);
            }

        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        try {

            prepareStatement(statement, object, false);

        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
//
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws Exception {
        try {
            prepareStatement(statement, object, true);

        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}