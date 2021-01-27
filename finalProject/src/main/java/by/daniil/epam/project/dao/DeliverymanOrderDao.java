package by.daniil.epam.project.dao;

import by.daniil.epam.project.domain.DeliverymanOrder;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface DeliverymanOrderDao extends Dao<DeliverymanOrder> {

    List<DeliverymanOrder> readByOrderId(Integer orderId) throws PersistentException;

    List<DeliverymanOrder> readByDeliverymanId(Integer deliverymanId) throws PersistentException;
}
