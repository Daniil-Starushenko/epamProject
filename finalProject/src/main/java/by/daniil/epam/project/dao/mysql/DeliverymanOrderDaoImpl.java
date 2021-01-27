package by.daniil.epam.project.dao.mysql;

import by.daniil.epam.project.dao.DeliverymanOrderDao;
import by.daniil.epam.project.domain.*;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliverymanOrderDaoImpl extends BaseDaoImpl implements DeliverymanOrderDao {
    Logger logger = LogManager.getLogger(DeliverymanOrderDaoImpl.class);

    @Override
    public List<DeliverymanOrder> readByOrderId(Integer orderId) throws PersistentException {
        String sql = "SELECT `id`, `deliveryman_id` FROM `deliveryman_order` WHERE `order_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            List<DeliverymanOrder> deliverymanOrderList = new ArrayList<>();
            DeliverymanOrder deliverymanOrder = null;
            Order order = new Order();
            order.setIdentity(orderId);
            while(resultSet.next()) {
                deliverymanOrder = new DeliverymanOrder();
                deliverymanOrder.setIdentity(resultSet.getInt("id"));
                deliverymanOrder.setOrder(order);
                DeliveryMan deliveryMan = new DeliveryMan();
                deliveryMan.setIdentity(resultSet.getInt("deliveryman_id"));
                deliverymanOrder.setDeliveryMan(deliveryMan);
                deliverymanOrderList.add(deliverymanOrder);
            }
            return deliverymanOrderList;
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public List<DeliverymanOrder> readByDeliverymanId(Integer deliverymanId) throws PersistentException {
        String sql = "SELECT `id`, `order_id` FROM `deliveryman_order` WHERE `deliveryman_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, deliverymanId);
            resultSet = statement.executeQuery();
            List<DeliverymanOrder> deliverymanOrderList = new ArrayList<>();
            DeliverymanOrder deliverymanOrder = null;
            DeliveryMan deliveryMan = new DeliveryMan();
            deliveryMan.setIdentity(deliverymanId);
            while(resultSet.next()) {
                deliverymanOrder = new DeliverymanOrder();
                deliverymanOrder.setIdentity(resultSet.getInt("id"));
                deliverymanOrder.setDeliveryMan(deliveryMan);
                Order order = new Order();
                order.setIdentity(resultSet.getInt("order_id"));
                deliverymanOrder.setOrder(order);
                deliverymanOrderList.add(deliverymanOrder);
            }
            return deliverymanOrderList;
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public Integer create(DeliverymanOrder entity) throws PersistentException {
        String sql = "INSERT INTO `deliveryman_order` (`deliveryman_id`, `order_id`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getDeliveryMan().getIdentity());
            statement.setInt(2, entity.getOrder().getIdentity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `users`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public DeliverymanOrder read(Integer identity) throws PersistentException {
        String sql = "SELECT `deliveryman_id`, `order_id` FROM `deliveryman_order` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            DeliverymanOrder deliverymanOrder = null;
            if (resultSet.next()) {
                deliverymanOrder = new DeliverymanOrder();
                deliverymanOrder.setIdentity(identity);
                Order order = new Order();
                order.setIdentity(resultSet.getInt("order_id"));
                deliverymanOrder.setOrder(order);
                DeliveryMan deliveryMan = new DeliveryMan();
                deliveryMan.setIdentity(resultSet.getInt("deliveryman_id"));
                deliverymanOrder.setDeliveryMan(deliveryMan);
            }
            return deliverymanOrder;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void update(DeliverymanOrder entity) throws PersistentException {
        String sql = "UPDATE `deliveryman_order` SET `deliveryman_id` = ?, `order_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getDeliveryMan().getIdentity());
            statement.setInt(2, entity.getOrder().getIdentity());
            statement.setInt(3, entity.getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        String sql = "DELETE FROM `deliveryman_order` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
