package by.daniil.epam.project.dao.mysql;

import by.daniil.epam.project.dao.OrderItemDao;
import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.domain.OrderItem;
import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl extends BaseDaoImpl implements OrderItemDao {
    Logger logger = LogManager.getLogger(OrderItemDaoImpl.class);

    @Override
    public Integer create(OrderItem entity) throws PersistentException {
        String sql = "INSERT INTO `order_item` (`order_id`, `product_id`, `quantity`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getOrder().getIdentity());
            statement.setInt(2, entity.getProductList().get(0).getIdentity());
            statement.setInt(3, entity.getQuantity());
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
    public OrderItem read(Integer identity) throws PersistentException {
        String sql = "SELECT `order_id`, `product_id`, `quantity` FROM `order_item` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            OrderItem orderItem = null;
            if (resultSet.next()) {
                orderItem = new OrderItem();
                orderItem.setIdentity(identity);
                Order order = new Order();
                order.setIdentity(resultSet.getInt("order_id"));
                orderItem.setOrder(order);
                Product product = new Product();
                product.setIdentity(resultSet.getInt("product_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setProductList(product, orderItem.getQuantity());
            }
            return orderItem;
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
    public void update(OrderItem entity) throws PersistentException {
        String sql = "UPDATE `order_item` SET `order_id` = ?, `product_id` = ?, `quantity` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getOrder().getIdentity());
            statement.setInt(2, entity.getProductList().get(0).getIdentity());
            statement.setInt(3, entity.getQuantity());
            statement.setInt(4, entity.getIdentity());
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
        String sql = "DELETE FROM `order_item` WHERE `id` = ?";
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

    @Override
    public List<OrderItem> readByOrder(Integer orderId) throws PersistentException {
        String sql = "SELECT `id`, `product_id`, `quantity` FROM `order_item` WHERE `order_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            List<OrderItem> orderItems = new ArrayList<>();
            OrderItem orderItem = null;
            Order order = new Order();
            order.setIdentity(orderId);
            while(resultSet.next()) {
                orderItem = new OrderItem();
                orderItem.setIdentity(resultSet.getInt("id"));
                orderItem.setOrder(order);
                orderItem.setQuantity(resultSet.getInt("quantity"));
                Product product = new Product();
                product.setIdentity(resultSet.getInt("product_id"));
                orderItem.setProductList(product, orderItem.getQuantity());
                orderItems.add(orderItem);
            }
            return orderItems;
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
    public List<OrderItem> readByProduct(Integer productId) throws PersistentException {
        String sql = "SELECT `id`, `order_id`, `quantity` FROM `order_item` WHERE `product_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();
            List<OrderItem> orderItems = new ArrayList<>();
            OrderItem orderItem = null;
            Product product = new Product();
            product.setIdentity(productId);
            while(resultSet.next()) {
                orderItem = new OrderItem();
                orderItem.setIdentity(resultSet.getInt("id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setProductList(product, orderItem.getQuantity());
                Order order = new Order();
                order.setIdentity(resultSet.getInt("order_id"));
                orderItem.setOrder(order);
                orderItems.add(orderItem);
            }
            return orderItems;
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
}
