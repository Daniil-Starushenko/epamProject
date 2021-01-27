package by.daniil.epam.project.dao;

import by.daniil.epam.project.domain.DeliveryMan;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface DeliveryManDao extends Dao<DeliveryMan> {

    List<DeliveryMan> read() throws PersistentException;

    DeliveryMan findByUserId(Integer id) throws PersistentException;

}
