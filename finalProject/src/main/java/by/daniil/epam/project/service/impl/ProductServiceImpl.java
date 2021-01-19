package by.daniil.epam.project.service.impl;

import by.daniil.epam.project.dao.ProductDao;
import by.daniil.epam.project.dao.mysql.ProductDaoImpl;
import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ProductService;
import by.daniil.epam.project.service.ServiceImpl;

import java.util.List;

public class ProductServiceImpl extends ServiceImpl implements ProductService {
    @Override
    public List<Product> findAll() throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        return productDao.read();
    }

    @Override
    public Product findById(Integer id) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDaoImpl.class);
        return productDao.read(id);
    }

    @Override
    public Product findByName(String name) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDaoImpl.class);
        return productDao.read(name);
    }

    @Override
    public void save(Product product) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDaoImpl.class);
        productDao.update(product);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDaoImpl.class);
        productDao.delete(id);
    }
}
