package by.daniil.epam.project.dao.transaction;

import by.daniil.epam.project.dao.transaction.Transaction;
import by.daniil.epam.project.exception.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;

    void close();
}
