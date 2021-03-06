package by.daniil.epam.project.service;

import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface OrderService extends Service {
    List<Order> findAll() throws PersistentException;

    Order findById(Integer id) throws PersistentException;

    List<Order> findByUser(Integer userId) throws PersistentException;

    List<Order> findByUserAndStatus(Integer userId, String status) throws PersistentException;

    List<Order> findByStatus(String status) throws PersistentException;

    void save(Order order) throws PersistentException;

    void delete(Integer id) throws  PersistentException;

    Integer create(Order order) throws PersistentException;
}
