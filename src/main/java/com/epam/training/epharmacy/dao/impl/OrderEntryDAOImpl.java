package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.OrderEntryDAO;
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

public class OrderEntryDAOImpl extends AbstractEntityDAO implements OrderEntryDAO {

    private static final String ADD_ORDER_ENTRY_SQL =
            "INSERT INTO order_entry ( order_number, medicines_id, package_amount) " +
                    "VALUES ((SELECT number FROM orders WHERE number = ?), (SELECT id FROM medicines WHERE serial_number = ?), ? )";
    private static final String UPDATE_ORDER_ENTRY_SQL = "UPDATE order_entry SET package_amount = ? WHERE order_number = ?";
    private static final String DELETE_ORDER_ENTRY_SQL = "DELETE FROM order_entry WHERE id = ?";

    private MedicineDAO medicineDAO;

    public OrderEntryDAOImpl(MedicineDAO medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    @Override
    protected String getInitialQuery() {
        return"SELECT id, order_number, medicines_id, package_amount FROM order_entry";
    }

    @Override
    public List<OrderEntry> findOrderEntryByCriteria(Criteria<SearchCriteria.OrderEntry> criteria) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<OrderEntry> orderEntries = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = createStatement(connection, criteria);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                OrderEntry orderEntry = new OrderEntry();
                orderEntry.setId(rs.getInt(ORDER_ENTRY_ID));
                orderEntry.setMedicine(getMedicineById(rs.getInt(ENTRY_MEDICINES_ID)));
                orderEntry.setOrderNumber(rs.getInt(ENTRY_ORDER_NUMBER));
                orderEntry.setPackageAmount(rs.getDouble(PACKAGE_AMOUNT));
                orderEntries.add(orderEntry);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during adding order", e);
        }
        return orderEntries;
    }

    private Medicine getMedicineById(int medicineId) throws DAOException, SQLException {
        Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Medicine.ID, medicineId);
        List<Medicine> medicines = medicineDAO.findMedicineByCriteria(criteria);
        if (!medicines.isEmpty())
        {
            return medicines.iterator().next();
        }
        return null;
    }

    @Override
    public void saveOrderEntry(OrderEntry orderEntry) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_ORDER_ENTRY_SQL, statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderEntry.getOrderNumber());
            statement.setString(2, orderEntry.getMedicine().getSerialNumber());
            statement.setDouble(3, orderEntry.getPackageAmount());

            statement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding order entry", e);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                orderEntry.setId(generatedKeys.getInt(1));
            }
        }

        finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public void deleteOrderEntry(OrderEntry orderEntry) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_ORDER_ENTRY_SQL);
            statement.setInt(1, orderEntry.getId());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during deleting order entry", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public void updateOrderEntry(OrderEntry orderEntry) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_ORDER_ENTRY_SQL);
            statement.setDouble(1, orderEntry.getPackageAmount());
            statement.setInt(2, orderEntry.getOrderNumber());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during updating order entry", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public List<OrderEntry> findEntryByOrderStatus() throws DAOException {
        return null;
    }
}
