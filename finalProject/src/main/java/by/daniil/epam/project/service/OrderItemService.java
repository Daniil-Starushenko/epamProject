package by.daniil.epam.project.service;

import by.daniil.epam.project.domain.OrderItem;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface OrderItemService extends Service {
    List<OrderItem> findByProductId(Integer id) throws PersistentException;

    List<OrderItem> findByOrderId(Integer id) throws PersistentException;

    void save(OrderItem orderItem) throws PersistentException;

    void delete(Integer id) throws  PersistentException;

}
