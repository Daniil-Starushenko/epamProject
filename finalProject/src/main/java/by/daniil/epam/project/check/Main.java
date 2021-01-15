package by.daniil.epam.project.check;

import by.daniil.epam.project.dao.UserDao;
import by.daniil.epam.project.dao.mysql.TransactionImpl;
import by.daniil.epam.project.dao.pool.ConnectionPool;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.core.util.JsonUtils;

import javax.crypto.spec.PSource;

public class Main {
    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/webShopDatabase?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;


    public static void main(String[] args) throws PersistentException {
        User user = new User();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        TransactionImpl transaction = new TransactionImpl(connectionPool.getConnection());
        user = transaction.createDao(UserDao.class).read(1);
        System.out.println();
    }
}
