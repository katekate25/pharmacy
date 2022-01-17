package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.ProducerDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;

public class ProducerDAOImp extends AbstractEntityDAO implements ProducerDAO {

    private static final String ADD_PRODUCER_SQL = "INSERT INTO producers (factory_name, producer_country) VALUES (?, ?)";

    @Override
    protected String getInitialQuery() {
        return "SELECT id, factory_name, producer_country FROM producers";
    }

    @Override
    public List<Producer> findProducerByCriteria(Criteria<SearchCriteria.Producer> criteria) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<Producer> producers = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = createStatement(connection, criteria);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Producer producer = new Producer();
                producer.setId(rs.getInt(PRODUCER_ID));
                producer.setProducerFactoryName(rs.getString(FACTORY_NAME));
                producer.setProducerCountry(rs.getString(COUNTRY));
                producers.add(producer);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during adding producer", e);
        }finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return producers;
    }

    @Override
    public void addProducer(Producer producer) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_PRODUCER_SQL, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, producer.getProducerFactoryName());
            statement.setString(2, producer.getProducerCountry());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding producer", e);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                producer.setId(generatedKeys.getInt(1));
            }
        }
        finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }


}
