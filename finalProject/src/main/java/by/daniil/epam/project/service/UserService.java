package by.daniil.epam.project.service;

import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll() throws PersistentException;

    User findById(Integer id) throws PersistentException;

    User findByLoginAndPassword(String login, String password) throws PersistentException;

    void save(User user) throws PersistentException;

    void delete(Integer id) throws  PersistentException;

    int create(User user) throws PersistentException;

}
