package by.daniil.epam.project.service.impl;

import by.daniil.epam.project.dao.DeliveryManDao;
import by.daniil.epam.project.domain.DeliveryMan;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.DeliverymanService;
import by.daniil.epam.project.service.ServiceImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

public class DeliveryManServiceImpl extends ServiceImpl implements DeliverymanService {
    @Override
    public List<DeliveryMan> findAll() throws PersistentException {
        DeliveryManDao deliveryManDao = transaction.createDao(DeliveryManDao.class);
        return deliveryManDao.read();
    }

    @Override
    public DeliveryMan findById(Integer id) throws PersistentException {
        DeliveryManDao deliveryManDao = transaction.createDao(DeliveryManDao.class);
        return deliveryManDao.read(id);
    }

    @Override
    public DeliveryMan findByUsrId(Integer id) throws PersistentException {
        DeliveryManDao deliveryManDao = transaction.createDao(DeliveryManDao.class);
        return deliveryManDao.findByUserId(id);
    }

    @Override
    public void save(DeliveryMan deliveryMan) throws PersistentException {
        DeliveryManDao deliveryManDao = transaction.createDao(DeliveryManDao.class);
        deliveryManDao.update(deliveryMan);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        DeliveryManDao deliveryManDao = transaction.createDao(DeliveryManDao.class);
        deliveryManDao.delete(id);
    }

    @Override
    public void create(DeliveryMan deliveryMan) throws PersistentException {
        DeliveryManDao deliveryManDao = transaction.createDao(DeliveryManDao.class);
        deliveryManDao.create(deliveryMan);
    }
}
