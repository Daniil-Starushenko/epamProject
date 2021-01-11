package by.daniil.epam.project.dao;

import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;

import java.util.List;

public interface UserDao extends Dao<User> {
    User read(String login, String password) throws PersistentException;

    List<User> read() throws PersistentException;
}
