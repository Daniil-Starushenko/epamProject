package by.daniil.epam.project.dao;

import by.daniil.epam.project.domain.Entity;
import by.daniil.epam.project.exception.PersistentException;

public interface Dao<Type extends Entity> {
    Integer create(Type entity) throws PersistentException;

    Type read(Integer identity) throws PersistentException;

    void update(Type entity) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
