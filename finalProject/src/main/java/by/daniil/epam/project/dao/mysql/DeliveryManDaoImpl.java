package by.daniil.epam.project.dao.mysql;

import by.daniil.epam.project.dao.DeliveryManDao;
import by.daniil.epam.project.domain.DeliveryMan;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryManDaoImpl extends BaseDaoImpl implements DeliveryManDao {
    Logger logger = LogManager.getLogger(DeliveryManDaoImpl.class);

    @Override
    public List<DeliveryMan> read() throws PersistentException {
        String sql = "SELECT `id`, `user_id`, `phone_number` FROM `deliveryman` ORDER BY `id`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<DeliveryMan> deliveryManList = new ArrayList<>();
            DeliveryMan deliveryMan = null;
            while(resultSet.next()) {
                deliveryMan = new DeliveryMan();
                deliveryMan.setIdentity(resultSet.getInt("id"));
                User user = new User();
                user.setIdentity(resultSet.getInt("user_id"));
                deliveryMan.setUser(user);
                deliveryMan.setPhoneNumber(resultSet.getString("phone_number"));
                deliveryManList.add(deliveryMan);
            }
            return deliveryManList;
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
    public Integer create(DeliveryMan entity) throws PersistentException {
        String sql = "INSERT INTO `deliveryman` (`user_id`, `phone_number`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getUser().getIdentity());
            statement.setString(2, entity.getPhoneNumber());
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
    public DeliveryMan read(Integer identity) throws PersistentException {
        String sql = "SELECT `user_id`, `phone_number`FROM `deliveryman` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            DeliveryMan deliveryMan = null;
            if (resultSet.next()) {
                deliveryMan = new DeliveryMan();
                deliveryMan.setIdentity(identity);
                User user = new User();
                user.setIdentity(resultSet.getInt("user_id"));
                deliveryMan.setUser(user);
                deliveryMan.setPhoneNumber(resultSet.getString("phone_number"));
            }
            return deliveryMan;
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
    public void update(DeliveryMan entity) throws PersistentException {
        String sql = "UPDATE `deliveryman` SET `user_id` = ?, `phone_number` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getUser().getIdentity());
            statement.setString(2, entity.getPhoneNumber());
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
        String sql = "DELETE FROM `deliveryman` WHERE `id` = ?";
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
