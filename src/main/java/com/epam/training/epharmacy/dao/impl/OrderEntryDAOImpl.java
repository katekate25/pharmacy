package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.OrderEntryDAO;
import com.epam.training.epharmacy.dao.PrescriptionDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;

public class OrderEntryDAOImpl extends AbstractEntityDAO implements OrderEntryDAO {

    private static final String ADD_ORDER_ENTRY_SQL =
            "INSERT INTO order_entry (order_number, medicines_id, package_amount, prescriptions_prescription_number) " +
                    "VALUES ((SELECT number FROM orders WHERE number = ?), (SELECT id FROM medicines WHERE serial_number = ?), ?, ?)";
    private static final String UPDATE_ORDER_ENTRY_SQL = "UPDATE order_entry SET package_amount = ?, prescriptions_prescription_number = ? WHERE id = ?";
    private static final String DELETE_ORDER_ENTRY_SQL = "DELETE FROM order_entry WHERE id = ?";
    private static final String FIND_ORDER_ENTRY_SQL = "SELECT id, order_number, medicines_id, package_amount, prescriptions_prescription_number FROM order_entry";

    private final MedicineDAO medicineDAO;
    private final PrescriptionDAO prescriptionDAO;

    public OrderEntryDAOImpl(MedicineDAO medicineDAO, PrescriptionDAO prescriptionDAO) {
        this.medicineDAO = medicineDAO;
        this.prescriptionDAO = prescriptionDAO;
    }

    @Override
    protected String getInitialQuery() {
        return FIND_ORDER_ENTRY_SQL;
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
                if (getPrescriptionByNumber(rs.getObject(ENTRY_PRESCRIPTION_NUMBER, Integer.class)) != null) {
                    orderEntry.setPrescription(getPrescriptionByNumber(rs.getInt(ENTRY_PRESCRIPTION_NUMBER)));
                }
                orderEntries.add(orderEntry);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during adding order", e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return orderEntries;
    }

    private Medicine getMedicineById(Integer medicineId) throws DAOException, SQLException {
        Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Medicine.ID, medicineId);
        List<Medicine> medicines = medicineDAO.findMedicineByCriteria(criteria);
        if (!medicines.isEmpty())
        {
            return medicines.iterator().next();
        }
        return null;
    }

    private Prescription getPrescriptionByNumber(Integer prescriptionNumber) {
        if (prescriptionNumber == null) {
            return null;
        }
        Criteria<SearchCriteria.Prescription> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Prescription.PRESCRIPTION_NUMBER, prescriptionNumber);
        List<Prescription> prescriptions = prescriptionDAO.findPrescriptionByCriteria(criteria);
        if (CollectionUtils.isEmpty(prescriptions)) {
            throw new DAOException(String.format("Error during finding prescription with number=%s", prescriptionNumber));
        }
        return prescriptions.iterator().next();
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
            statement.setObject(4, getPrescriptionNumberFromEntry(orderEntry), Types.INTEGER);
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
            statement.setObject(2, getPrescriptionNumberFromEntry(orderEntry), Types.INTEGER);
            statement.setInt(3, orderEntry.getId());

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

    private Integer getPrescriptionNumberFromEntry(OrderEntry entry) {
        return entry.getPrescription() != null ? entry.getPrescription().getPrescriptionNumber() : null;
    }
}
