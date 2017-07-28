package ru.bellintegrator.app.dao;

import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by neste_000 on 21.07.2017.
 */
public interface GenericDAO<T extends Serializable> {

    int create(T t) throws DAOException;

    void delete(T t) throws DAOException;

    void update(T t) throws DAOException;

    List<T> getAll() throws DAOException;

    void save(List<T> list) throws DAOException;

    T getById(int id);

    List<T> getByName(String name);

}
