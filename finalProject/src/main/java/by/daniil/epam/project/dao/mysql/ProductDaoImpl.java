package by.daniil.epam.project.dao.mysql;

import by.daniil.epam.project.dao.ProductDao;
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

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
    private static Logger logger = LogManager.getLogger(ProductDaoImpl.class);

    @Override
    public Integer create(Product product) throws PersistentException {
        String sql = "INSERT INTO `product` (`name`, `png_path`, `definition`, `price`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getPngPath());
            statement.setString(3, product.getDefinition());
            statement.setDouble(4, product.getPrice());
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
    public Product read(String name) throws PersistentException {
        String sql = "SELECT `id`, `png_path`, `definition`, `price` FROM `product` WHERE `name` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                product = new Product();
                product.setIdentity(resultSet.getInt("id"));
                product.setProductName(name);
                product.setPngPath(resultSet.getString("png_path"));
                product.setDefinition(resultSet.getString("definition"));
                product.setPrice(resultSet.getDouble("price"));
            }
            return product;
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
    public List<Product> read() throws PersistentException {
        String sql = "SELECT `id`, `name`, `png_path`, `definition`, `price` FROM `product` ORDER BY `name`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            Product product = null;
            while(resultSet.next()) {
                product = new Product();
                product.setIdentity(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("name"));
                product.setPngPath(resultSet.getString("png_path"));
                product.setDefinition(resultSet.getString("definition"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
            return products;
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
    public Product read(Integer id) throws PersistentException {
        String sql = "SELECT `name`, `png_path`, `definition`, `price` FROM `product` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                product = new Product();
                product.setIdentity(id);
                product.setProductName(resultSet.getString("name"));
                product.setPngPath(resultSet.getString("png_path"));
                product.setDefinition(resultSet.getString("definition"));
                product.setPrice(resultSet.getDouble("price"));
            }
            return product;
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
    public void update(Product product) throws PersistentException {
        String sql = "UPDATE `product` SET `name` = ?, `png_path` = ?, `definition` = ?, `price` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getPngPath());
            statement.setString(3, product.getDefinition());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getIdentity());
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
        String sql = "DELETE FROM `product` WHERE `id` = ?";
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
