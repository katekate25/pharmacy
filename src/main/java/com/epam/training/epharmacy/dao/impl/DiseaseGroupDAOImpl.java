package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.DiseaseGroupDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.DiseaseGroup;
import com.epam.training.epharmacy.entity.SearchCriteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DiseaseGroupDAOImpl extends AbstractEntityDAO implements DiseaseGroupDAO {

    private static final String ADD_DISEASE_GROUP = "INSERT INTO disease_groups (code) VALUES (?)";

    @Override
    public void addDiseaseGroup(DiseaseGroup diseaseGroup) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_DISEASE_GROUP);
            statement.setString(1, diseaseGroup.name());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding disease group", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public List<DiseaseGroup> findDiseaseGroupByCriteria(Criteria<SearchCriteria.DiseaseGroup> criteria) throws DAOException {
        return null;
    }

    @Override
    protected String getInitialQuery() {
        return "SELECT code FROM disease_groups";
    }
}
