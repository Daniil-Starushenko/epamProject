package by.daniil.epam.project.dao.mysql;

import by.daniil.epam.project.dao.pool.ConnectionPool;
import by.daniil.epam.project.dao.transaction.Transaction;
import by.daniil.epam.project.dao.transaction.TransactionFactory;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private static Logger logger = LogManager.getLogger(TransactionFactoryImpl.class);
    private Connection connection;

    public TransactionFactoryImpl() throws PersistentException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws PersistentException {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {}
    }
}
