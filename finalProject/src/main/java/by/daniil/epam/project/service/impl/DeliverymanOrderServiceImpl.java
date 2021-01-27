package by.daniil.epam.project.service.impl;

import by.daniil.epam.project.dao.DeliverymanOrderDao;
import by.daniil.epam.project.domain.DeliverymanOrder;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.DeliverymanOrderService;
import by.daniil.epam.project.service.ServiceImpl;

import java.util.List;

public class DeliverymanOrderServiceImpl extends ServiceImpl implements DeliverymanOrderService {
    @Override
    public List<DeliverymanOrder> findByOrderId(Integer orderId) throws PersistentException {
        DeliverymanOrderDao deliverymanOrderDao = transaction.createDao(DeliverymanOrderDao.class);
        return deliverymanOrderDao.readByOrderId(orderId);
    }

    @Override
    public List<DeliverymanOrder> findByDeliverymanId(Integer id) throws PersistentException {
        DeliverymanOrderDao deliverymanOrderDao = transaction.createDao(DeliverymanOrderDao.class);
        return deliverymanOrderDao.readByDeliverymanId(id);
    }

    @Override
    public void save(DeliverymanOrder deliverymanOrder) throws PersistentException {
        DeliverymanOrderDao deliverymanOrderDao = transaction.createDao(DeliverymanOrderDao.class);
        deliverymanOrderDao.update(deliverymanOrder);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        DeliverymanOrderDao deliverymanOrderDao = transaction.createDao(DeliverymanOrderDao.class);
        deliverymanOrderDao.delete(id);
    }

    @Override
    public int create(DeliverymanOrder deliverymanOrder) throws PersistentException {
        DeliverymanOrderDao deliverymanOrderDao = transaction.createDao(DeliverymanOrderDao.class);
        return deliverymanOrderDao.create(deliverymanOrder);
    }
}
