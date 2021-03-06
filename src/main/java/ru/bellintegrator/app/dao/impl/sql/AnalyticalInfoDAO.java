package ru.bellintegrator.app.dao.impl.sql;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;

import java.util.Map;

public interface AnalyticalInfoDAO extends GenericDAO<AnalyticalInfo>{

    int getUserCount() throws DAOException;

    Map<Integer, Integer> getEachUserContactCount() throws DAOException;

    Map<Integer, Integer> getEachUserGroupCount() throws DAOException;

    double getAvgUserCountInGroup() throws DAOException;

    int getInactiveUserCount() throws DAOException;

    double getAvgUserContactsCount() throws DAOException;

}
