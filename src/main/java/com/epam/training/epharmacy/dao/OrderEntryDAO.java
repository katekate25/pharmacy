package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.OrderEntry;
import com.epam.training.epharmacy.entity.SearchCriteria;

import java.sql.SQLException;
import java.util.List;

public interface OrderEntryDAO {
    List<OrderEntry> findOrderEntryByCriteria(Criteria<SearchCriteria.OrderEntry> criteria) throws DAOException, SQLException;

    void saveOrderEntry(OrderEntry orderEntry) throws DAOException, SQLException;

    void deleteOrderEntry(OrderEntry orderEntry) throws DAOException;

    void updateOrderEntry(OrderEntry orderEntry) throws DAOException;

    List<OrderEntry> findEntryByOrderStatus () throws DAOException;
}


