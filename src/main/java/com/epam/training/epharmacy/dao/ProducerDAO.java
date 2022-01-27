package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.Producer;
import com.epam.training.epharmacy.entity.SearchCriteria;

import java.sql.SQLException;
import java.util.List;

public interface ProducerDAO {

    List<Producer> findProducerByCriteria(Criteria<SearchCriteria.Producer> criteria) throws DAOException, SQLException;

    void addProducer(Producer producer) throws DAOException, SQLException;
}
