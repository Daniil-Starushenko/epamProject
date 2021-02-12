package by.daniil.epam.project.service.impl;

import by.daniil.epam.project.dao.UserDao;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ServiceImpl;
import by.daniil.epam.project.service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {
    @Override
    public List<User> findAll() throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read();
    }

    @Override
    public User findById(Integer id) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read(id);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read(login, md5(password));
    }

    @Override
    public void save(User user) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);

    }

    @Override
    public void delete(Integer id) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        userDao.delete(id);
    }

    @Override
    public int create(User user) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        user.setPassword(md5(user.getPassword()));
        return userDao.create(user);
    }

    private String md5(String string) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.reset();
            digest.update(string.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for(int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String md5sum = formatter.toString();
            formatter.close();
            return md5sum;
        } catch(NoSuchAlgorithmException e) {
            return null;
        }
    }
}
