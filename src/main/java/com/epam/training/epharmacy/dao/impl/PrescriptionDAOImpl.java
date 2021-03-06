package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.PrescriptionDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;

public class PrescriptionDAOImpl extends AbstractEntityDAO implements PrescriptionDAO {

    private static final String ADD_PRESCRIPTION_SQL =
            "INSERT INTO prescriptions (medicines_id, number_of_packages," +
                    "usage_instruction, creation_date, expiration_date, client_id, doctor_id, status) " +
                    "VALUES ((SELECT id FROM medicines WHERE serial_number=?), ?, ?, ?, ?," +
                    "(SELECT id FROM users WHERE login=?), (SELECT id FROM users WHERE login=?), ?)";

    private static final String DELETE_PRESCRIPTION_SQL = "DELETE FROM prescriptions WHERE prescription_number = ?";

    private static final String UPDATE_PRESCRIPTION_SQL = "UPDATE prescriptions SET status = ? WHERE prescription_number = ?";


    private final MedicineDAO medicineDAO;
    private final UserDAO userDAO;

    public PrescriptionDAOImpl(MedicineDAO medicineDAO, UserDAO userDAO) {
        this.medicineDAO = medicineDAO;
        this.userDAO = userDAO;
    }

    @Override
    protected String getInitialQuery() {
        return "SELECT prescription_number, medicines_id, number_of_packages, usage_instruction, " +
                "creation_date, expiration_date, client_id, doctor_id, status FROM prescriptions";
    }

    @Override
    public List<Prescription> findPrescriptionByCriteria(Criteria<SearchCriteria.Prescription> criteria) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<Prescription> prescriptions = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = createStatement(connection, criteria);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Prescription prescription = new Prescription.Builder()
                        .prescriptionNumber(rs.getInt(PRESCRIPTION_NUMBER))
                        .medicine(getMedicineById(rs.getInt(MEDICINES_ID)) )
                        .packageAmount(rs.getDouble(NUMBER_OF_PACKAGES))
                        .client(getUserById(rs.getInt(CLIENT_ID)))
                        .doctor(getUserById(rs.getInt(DOCTOR_ID)))
                        .creationDate(rs.getDate(CREATION_DATE))
                        .expirationDate(rs.getDate(PRESCRIPTION_EXPIRATION_DATE))
                        .build();
                prescription.setStatus(PrescriptionStatus.valueOf(rs.getString(PRESCRIPTION_STATUS)));

                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during adding prescription", e);
        }finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return prescriptions;
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

    private User getUserById(int userId) throws SQLException {
        Criteria<SearchCriteria.User> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.User.ID, userId);
        List<User> users = userDAO.findUserByCriteria(criteria);
        if (!users.isEmpty())
        {
            return users.iterator().next();
        }
        return null;
    }

    @Override
    public void addPrescription(Prescription prescription) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_PRESCRIPTION_SQL, statement.RETURN_GENERATED_KEYS);
//            statement.setInt(1, prescription.getPrescriptionNumber());
            statement.setString(1, prescription.getMedicine().getSerialNumber());
            statement.setDouble(2, prescription.getPackageAmount());
            statement.setString(3, prescription.getUsageInstruction());
            statement.setDate(4, new Date(prescription.getCreationDate().getTime()));
            statement.setDate(5, new Date(prescription.getExpirationDate().getTime()));
            statement.setString(6, prescription.getClient().getLogin());
            statement.setString(7, prescription.getDoctor().getLogin());
            statement.setString(8, String.valueOf(prescription.getStatus()));

            statement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding prescription", e);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                prescription.setPrescriptionNumber(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException("Error during getting id for prescription", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public void deletePrescription(int prescriptionNumber) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_PRESCRIPTION_SQL);
            statement.setInt(1, prescriptionNumber);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during deleting prescription", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public void updatePrescription(Prescription prescription) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_PRESCRIPTION_SQL);
            statement.setString(1, String.valueOf(prescription.getStatus()));
            statement.setInt(2, prescription.getPrescriptionNumber());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during updating prescription", e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }


}
