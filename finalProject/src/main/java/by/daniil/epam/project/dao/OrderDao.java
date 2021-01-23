package by.daniil.epam.project.dao;

import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.domain.OrderingStatus;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    List<Order> read() throws PersistentException;

    List<Order> readByUserId(Integer userId) throws PersistentException;

    List<Order> readByStatusAndUser(Integer userId, String status) throws PersistentException;
}
