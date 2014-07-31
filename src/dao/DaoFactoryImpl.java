package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DaoFactoryImpl implements DaoFactory{

    private static Connection connection = null;

    private String user = "";
    private String password = "";
    private String url = "";
    private String driver = "";

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if ((this.connection != null) && !this.connection.isClosed()) {
            return this.connection;
        } else {
            try {

                Properties props = new Properties();
                FileInputStream in = new FileInputStream("database.properties");
                props.load(in);
                in.close();

                String driver = props.getProperty("db.driver");

                if (driver != null) this.driver = driver;

                this.url = props.getProperty("db.url");
                this.user = props.getProperty("db.username");
                this.password = props.getProperty("db.password");

                Class.forName(this.driver);

                this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            } catch (SQLException e) {
                throw new SQLException(e);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this.connection;
        }
    }

    @Override
    public GenericDao getDao(Class Dao) throws Exception {
        return (GenericDao)Dao.getConstructor(Connection.class).newInstance(getConnection());
    }

    protected void finalize() throws Throwable {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}