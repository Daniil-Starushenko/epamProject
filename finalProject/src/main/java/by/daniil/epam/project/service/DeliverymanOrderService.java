package by.daniil.epam.project.service;

import by.daniil.epam.project.domain.DeliverymanOrder;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface DeliverymanOrderService extends Service {
    List<DeliverymanOrder> findByOrderId(Integer orderId) throws PersistentException;

    List<DeliverymanOrder> findByDeliverymanId(Integer deliverymanId) throws PersistentException;

    void save(DeliverymanOrder deliverymanOrder) throws PersistentException;

    void delete(Integer id) throws  PersistentException;

    int create(DeliverymanOrder deliverymanOrder) throws PersistentException;
}
