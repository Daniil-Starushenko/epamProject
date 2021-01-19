package by.daniil.epam.project;

import by.daniil.epam.project.dao.ProductDao;
import by.daniil.epam.project.dao.mysql.TransactionImpl;
import by.daniil.epam.project.dao.pool.ConnectionPool;
import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public class Main {
    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/webShopDatabase?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public static void main(String[] args) throws PersistentException {
        List<Product> productList = null;



        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        TransactionImpl transaction = new TransactionImpl(connectionPool.getConnection());

        productList = transaction.createDao(ProductDao.class).read();
        System.out.println();
    }
}
