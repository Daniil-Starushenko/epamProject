package by.daniil.epam.project.dao.mysql;

import by.daniil.epam.project.dao.OrderDao;
import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.domain.OrderingStatus;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    private static Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    @Override
    public Integer create(Order entity) throws PersistentException {
        String sql = "INSERT INTO `order` (`user_id`, `address`, `date_of_ordering`, `phone_number`, `total_price`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getCustomer().getIdentity());
            statement.setString(2, entity.getAddress());
            statement.setString(3, entity.getDateOfOrdering());
            statement.setString(4, entity.getPhoneNumber());
            statement.setDouble(5, entity.getTotalPrice());
            statement.setString(6, entity.getStatus().toString());
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
    public List<Order> read() throws PersistentException {
        String sql = "SELECT `id`, `user_id`, `address`, `date_of_ordering`, `phone_number`, `total_price`, `status` FROM `order` ORDER BY `id`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order = null;
            while(resultSet.next()) {
                order = new Order();
                order.setIdentity(resultSet.getInt("id"));
                User user = new User();
                user.setIdentity(resultSet.getInt("user_id"));
                order.setCustomer(user);
                order.setAddress(resultSet.getString("address"));
                order.setDateOfOrdering(resultSet.getString("date_of_ordering"));
                order.setPhoneNumber(resultSet.getString("phone_number"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setStatus(OrderingStatus.getByTag(resultSet.getString("status")));
                orders.add(order);
            }
            return orders;
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
    public List<Order> readByUserId(Integer userId) throws PersistentException {
        String sql = "SELECT `id`, `address`, `date_of_ordering`, `phone_number`, `total_price`, `status` FROM `order` WHERE `user_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order = null;
            User user = new User();
            user.setIdentity(userId);
            while(resultSet.next()) {
                order = new Order();
                order.setIdentity(resultSet.getInt("id"));
                order.setCustomer(user);
                order.setAddress(resultSet.getString("address"));
                order.setDateOfOrdering(resultSet.getString("date_of_ordering"));
                order.setPhoneNumber(resultSet.getString("phone_number"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setStatus(OrderingStatus.getByTag(resultSet.getString("status")));
                orders.add(order);
            }
            return orders;
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
    public List<Order> readByStatusAndUser(Integer userId, String status) throws PersistentException {
        String sql = "SELECT `id`, `address`, `date_of_ordering`, `phone_number`, `total_price` FROM `order` WHERE `user_id` = ? AND `status` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, status);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order = null;
            User user = new User();
            user.setIdentity(userId);
            while(resultSet.next()) {
                order = new Order();
                order.setIdentity(resultSet.getInt("id"));
                order.setCustomer(user);
                order.setAddress(resultSet.getString("address"));
                order.setDateOfOrdering(resultSet.getString("date_of_ordering"));
                order.setPhoneNumber(resultSet.getString("phone_number"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setStatus(OrderingStatus.getByTag(status));
                orders.add(order);
            }
            return orders;
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
    public List<Order> readByStatus(String status) throws PersistentException {
        String sql = "SELECT `id`, `user_id`,`address`, `date_of_ordering`, `phone_number`, `total_price` FROM `order` WHERE `status` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order = null;
            while(resultSet.next()) {
                order = new Order();
                order.setIdentity(resultSet.getInt("id"));
                User user = new User();
                user.setIdentity(resultSet.getInt("user_id"));
                order.setCustomer(user);
                order.setAddress(resultSet.getString("address"));
                order.setDateOfOrdering(resultSet.getString("date_of_ordering"));
                order.setPhoneNumber(resultSet.getString("phone_number"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setStatus(OrderingStatus.getByTag(status));
                orders.add(order);
            }
            return orders;
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
    public Order read(Integer id) throws PersistentException {
        String sql = "SELECT `user_id`, `address`, `date_of_ordering`, `phone_number`, `total_price`, `status` FROM `order` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setIdentity(id);
                User user = new User();
                user.setIdentity(resultSet.getInt("user_id"));
                order.setCustomer(user);
                order.setAddress(resultSet.getString("address"));
                order.setDateOfOrdering(resultSet.getString("date_of_ordering"));
                order.setPhoneNumber(resultSet.getString("phone_number"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setStatus(OrderingStatus.getByTag(resultSet.getString("status")));
            }
            return order;
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
    public void update(Order entity) throws PersistentException {
        String sql = "UPDATE `order` SET `user_id` = ?, `address` = ?, `date_of_ordering` = ?, `phone_number` = ?, `total_price` = ?, `status` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getCustomer().getIdentity());
            statement.setString(2, entity.getAddress());
            statement.setString(3, entity.getDateOfOrdering());
            statement.setString(4, entity.getPhoneNumber());
            statement.setDate(5, (Date) entity.getOrderProducts());
            statement.setInt(6, entity.getStatus().getIdentity());
            statement.setInt(7, entity.getIdentity());
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
        String sql = "DELETE FROM `order` WHERE `id` = ?";
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
