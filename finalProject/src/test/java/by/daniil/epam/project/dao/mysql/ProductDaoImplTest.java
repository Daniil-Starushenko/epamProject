package by.daniil.epam.project.dao.mysql;

import by.daniil.epam.project.dao.ProductDao;
import by.daniil.epam.project.dao.pool.ConnectionPool;
import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDaoImplTest {
    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/webShopDatabase";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    ConnectionPool connectionPool = ConnectionPool.getInstance();
    TransactionImpl transaction;
    Product product = new Product();

    public ProductDaoImplTest() throws PersistentException {
    }

    @BeforeMethod
    public void setUp() throws PersistentException {
        connectionPool.init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        transaction = new TransactionImpl(connectionPool.getConnection());

        product.setIdentity(5);
        product.setProductName("testName");
        product.setPngPath("testPath");
        product.setDefinition("testDefinition");
        product.setPrice(12.50);
    }

    @AfterMethod
    public boolean tearDown() throws PersistentException {
        return product.equals(testCreate());
    }

    @Test
    public Product testCreate() throws PersistentException {
        transaction.createDao(ProductDao.class).create(product);
        return transaction.createDao(ProductDao.class).read(5);
    }
}