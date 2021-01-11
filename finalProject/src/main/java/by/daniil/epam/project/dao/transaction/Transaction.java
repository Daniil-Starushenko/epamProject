package by.daniil.epam.project.dao.transaction;

import by.daniil.epam.project.dao.Dao;
import by.daniil.epam.project.exception.PersistentException;

public interface Transaction {
    <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
