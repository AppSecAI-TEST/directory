package ru.bellintegrator.app.dao.factory.impl.sql;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.sql.postgresql.PostgresqlContactDAO;
import ru.bellintegrator.app.dao.impl.sql.postgresql.PostgresqlGroupDAO;
import ru.bellintegrator.app.dao.impl.sql.postgresql.PostgresqlUserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.util.IdGenerator;

public class SqlPostgresqlDAOFactory extends AbstractSqlDAOFactory {

    @Override
    public GenericDAO<Contact> getContactDAO() throws DAOException {
        PostgresqlContactDAO dao = new PostgresqlContactDAO();
        dao.setIdGenerator(new IdGenerator(dao.getAll()));

        return dao;
    }

    @Override
    public GenericDAO<Group> getGroupDAO() throws DAOException {
        PostgresqlGroupDAO dao = new PostgresqlGroupDAO();
        dao.setIdGenerator(new IdGenerator(dao.getAll()));

        return dao;
    }

    @Override
    public GenericDAO<User> getUserDAO() throws DAOException {
        PostgresqlUserDAO dao = new PostgresqlUserDAO();
        dao.setIdGenerator(new IdGenerator(dao.getAll()));

        return dao;
    }

    @Override
    public GenericDAO<AnalyticalInfo> getAnalyticalInfoDAO() {
        return null;
    }

}
