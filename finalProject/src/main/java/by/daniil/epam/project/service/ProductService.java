package by.daniil.epam.project.service;

import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface ProductService extends Service {
    List<Product> findAll() throws PersistentException;

    Product findById(Integer id) throws PersistentException;

    Product findByName(String name) throws PersistentException;

    void save(Product product) throws PersistentException;

    void delete(Integer id) throws  PersistentException;
}
