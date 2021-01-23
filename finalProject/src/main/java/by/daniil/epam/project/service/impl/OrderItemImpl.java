package by.daniil.epam.project.service.impl;

import by.daniil.epam.project.dao.OrderItemDao;
import by.daniil.epam.project.domain.OrderItem;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.OrderItemService;
import by.daniil.epam.project.service.ServiceImpl;

import java.util.List;

public class OrderItemImpl extends ServiceImpl implements OrderItemService {
    @Override
    public List<OrderItem> findByProductId(Integer id) throws PersistentException {
        OrderItemDao orderItemDao = transaction.createDao(OrderItemDao.class);
        return orderItemDao.readByProduct(id);
    }

    @Override
    public List<OrderItem> findByOrderId(Integer id) throws PersistentException {
        OrderItemDao orderItemDao = transaction.createDao(OrderItemDao.class);
        return orderItemDao.readByOrder(id);
    }

    @Override
    public void save(OrderItem orderItem) throws PersistentException {
        OrderItemDao orderItemDao = transaction.createDao(OrderItemDao.class);
        orderItemDao.update(orderItem);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        OrderItemDao orderItemDao = transaction.createDao(OrderItemDao.class);
        orderItemDao.delete(id);
    }

    @Override
    public void create(OrderItem orderItem) throws PersistentException {
        OrderItemDao orderItemDao = transaction.createDao(OrderItemDao.class);
        orderItemDao.create(orderItem);
    }
}
