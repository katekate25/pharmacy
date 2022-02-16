package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    List<Order> findOrderByCriteria(Criteria<SearchCriteria.Order> criteria) throws DAOException, SQLException;

    void saveOrder(Order order) throws DAOException, SQLException;

    void updateOrder(Order order) throws DAOException;
}
