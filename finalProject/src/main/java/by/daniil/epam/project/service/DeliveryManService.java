package by.daniil.epam.project.service;

import by.daniil.epam.project.domain.DeliveryMan;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface DeliveryManService extends Service {
    List<DeliveryMan> findAll() throws PersistentException;

    DeliveryMan findById(Integer id) throws PersistentException;

    DeliveryMan findByUsrId(Integer id) throws PersistentException;

    void save(DeliveryMan deliveryMan) throws PersistentException;

    void delete(Integer id) throws  PersistentException;

    void create(DeliveryMan deliveryMan) throws PersistentException;
}
