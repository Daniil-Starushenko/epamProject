package by.daniil.epam.project.service;

import by.daniil.epam.project.exception.PersistentException;

public interface ServiceFactory {
    <Type extends Service> Type getService(Class<Type> key) throws PersistentException;

    void close();
}
