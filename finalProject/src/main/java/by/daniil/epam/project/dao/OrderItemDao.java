package by.daniil.epam.project.dao;

import by.daniil.epam.project.domain.OrderItem;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface OrderItemDao extends Dao<OrderItem> {
    List<OrderItem> readByOrder(Integer id) throws PersistentException;

    List<OrderItem> readByProduct(Integer id) throws PersistentException;
}
