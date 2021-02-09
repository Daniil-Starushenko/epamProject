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
    public List<Product> findLimitedNumberOfProducts(int limit, int offset) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        return productDao.limitedRead(limit, offset);
    }

    @Override
    public int countNumberOfRows() throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        return productDao.readRowCount();
    }

    @Override
    public Product findById(Integer id) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        return productDao.read(id);
    }

    @Override
    public Product findByName(String name) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        return productDao.read(name);
    }

    @Override
    public void save(Product product) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        productDao.update(product);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        productDao.delete(id);
    }

    @Override
    public void create(Product product) throws PersistentException {
        ProductDao productDao = transaction.createDao(ProductDao.class);
        productDao.create(product);
    }

}
