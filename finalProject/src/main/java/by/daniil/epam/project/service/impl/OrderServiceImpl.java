package by.daniil.epam.project.service.impl;

import by.daniil.epam.project.dao.OrderDao;
import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.OrderService;
import by.daniil.epam.project.service.ServiceImpl;

import java.util.List;

public class OrderServiceImpl  extends ServiceImpl implements OrderService {

    @Override
    public List<Order> findAll() throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        return orderDao.read();
    }

    @Override
    public Order findById(Integer id) throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        return orderDao.read(id);
    }

    @Override
    public List<Order> findByUser(Integer userId) throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        return orderDao.readByUserId(userId);
    }

    @Override
    public List<Order> findByUserAndStatus(Integer userId, String status) throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        return orderDao.readByStatusAndUser(userId, status);
    }

    @Override
    public List<Order> findByStatus(String status) throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        return orderDao.readByStatus(status);
    }


    @Override
    public void save(Order order) throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        orderDao.update(order);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        orderDao.delete(id);
    }

    @Override
    public Integer create(Order order) throws PersistentException {
        OrderDao orderDao = transaction.createDao(OrderDao.class);
        return orderDao.create(order);
    }
}
