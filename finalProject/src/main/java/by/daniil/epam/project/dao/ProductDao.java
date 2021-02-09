package by.daniil.epam.project.dao;

import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface ProductDao extends Dao<Product> {
    Product read(String name) throws PersistentException;

    List<Product> read() throws PersistentException;

    List<Product> limitedRead(int limit, int offset) throws PersistentException;

    int readRowCount() throws PersistentException;
}
