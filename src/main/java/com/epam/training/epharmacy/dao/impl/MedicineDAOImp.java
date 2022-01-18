package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.ProducerDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;

public class MedicineDAOImp extends AbstractEntityDAO implements MedicineDAO {

    private static final String ADD_MEDICINE_SQL =
            "INSERT INTO medicines (commercial_name, international_name, dose, medicine_form, invoice_number, serial_number, price, " +
                    "expiration_date, product_arrival, arrival_date, balance, prescription_required, producers_id, disease_groups_code) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id FROM producers WHERE factory_name=?), (SELECT code FROM disease_groups WHERE code=?))";
    private static final String DELETE_MEDICINE_SQL = "DELETE FROM medicines WHERE serial_number = ?";
    private static final String UPDATE_MEDICINE_SQL = "UPDATE medicines SET product_arrival = ?, balance = ?, invoice_number=?, arrival_date=?, price=?  WHERE serial_number = ?";
    private static final String SHOW_MEDICINE_LIST_SQL = "SELECT * FROM medicines";

    private ProducerDAO producerDAO;

    public MedicineDAOImp(ProducerDAO producerDAO) {
        this.producerDAO = producerDAO;
    }

    @Override
    protected String getInitialQuery() {
        return "SELECT id, commercial_name, international_name, dose, medicine_form, invoice_number, serial_number, price, " +
                "expiration_date, product_arrival, arrival_date, balance, prescription_required, producers_id, disease_groups_code FROM medicines";
    }

    @Override
    public void addORUpdateMedicine(Medicine newMedicine) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        String serialNumber = newMedicine.getSerialNumber();

        MedicineDAO medicineDAO = DAOFactory.getInstance().getMedicineDAO();
        Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, serialNumber);
        List<Medicine> medicines = medicineDAO.findMedicineByCriteria(criteria);

        if (medicines == null || medicines.isEmpty()) {
            addMedicine(newMedicine);
        } else {
            Medicine existMedicine = medicines.iterator().next();
            existMedicine.setProductArrival(existMedicine.getProductArrival() + newMedicine.getProductArrival());
            existMedicine.setProductBalance(existMedicine.getProductBalance() + newMedicine.getProductArrival());
            updateMedicine(existMedicine);
        }
            ConnectionPool.getInstance().closeConnection(connection, statement);

    }

    @Override
    public void addMedicine(Medicine medicine) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_MEDICINE_SQL, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, medicine.getCommercialName());
            statement.setString(2, medicine.getInternationalName());
            statement.setInt(3, medicine.getMedicineDose());
            statement.setString(4, medicine.getMedicineForm());
            statement.setString(5, medicine.getInvoiceNumber());
            statement.setString(6, medicine.getSerialNumber());
            statement.setDouble(7, medicine.getPackagePrice());
            statement.setDate(8, new Date(medicine.getMedicineExpirationDate().getTime()));
            statement.setDouble(9, medicine.getProductArrival());
            statement.setDate(10, new Date(medicine.getArrivalDate().getTime()));
            statement.setDouble(11, medicine.getProductBalance());
            statement.setBoolean(12, medicine.isPrescriptionRequired());
            statement.setString(13, medicine.getProducer().getProducerFactoryName());
            statement.setString(14, medicine.getDiseaseGroup().name());

            statement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding medicine", e);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                medicine.setId(generatedKeys.getInt(1));
            }
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public List<Medicine> findMedicineByCriteria(Criteria<SearchCriteria.Medicine> criteria) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<Medicine> medicines = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = createStatement(connection, criteria);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Medicine medicine = new Medicine.Builder()
                        .id(rs.getInt(MEDICINE_ID))
                        .commercialName(rs.getString(COMMERCIAL_NAME))
                        .internationalName(rs.getString(INTERNATIONAL_NAME))
                        .medicineForm(rs.getString(FORM))
                        .medicineDose(rs.getInt(DOSE))
                        .productArrival(rs.getDouble(PRODUCT_ARRIVAL))
                        .diseaseGroup(DiseaseGroup.valueOf(rs.getString(DISEASE_GROUP)))
                        .arrivalDate(rs.getDate(ARRIVAL_DATE))
                        .invoiceNumber(rs.getString(INVOICE_NUMBER))
                        .medicineExpirationDate(rs.getDate(EXPIRATION_DATE))
                        .isPrescriptionRequired(rs.getBoolean(PRESCRIPTION_REQUIRED))
                        .packagePrice(rs.getDouble(PRICE))
                        .producer(getProducerById(rs.getInt(PRODUCER_ID)))
                        .serialNumber(rs.getString(SERIAL_NUMBER))
                        .productBalance(rs.getDouble(PRODUCT_BALANCE))
                        .build();

                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during adding medicine", e);
        }finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return medicines;
    }

    private Producer getProducerById(int producerId) throws DAOException, SQLException {
        Criteria<SearchCriteria.Producer> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Producer.ID, producerId);
        List<Producer> producers = producerDAO.findProducerByCriteria(criteria);
        if (!producers.isEmpty()) {
            return producers.iterator().next();
        }
        return null;
    }

    @Override
    public void deleteMedicine(Medicine medicine) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_MEDICINE_SQL);
            statement.setString(1, medicine.getSerialNumber());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during deleting prescription", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public List<Medicine> showMedicineList() throws DAOException, SQLException {
//        PreparedStatement statement = null;
//        Connection connection = null;
//        List<Medicine> medicines = new ArrayList<>();
//        try {
//            connection = ConnectionPool.getInstance().takeConnection();
//            ResultSet rs = statement.executeQuery();
//            while(rs.next()){
//                Medicine medicine = new Medicine.Builder()
//                        .id(rs.getInt(MEDICINE_ID))
//                        .commercialName(rs.getString(COMMERCIAL_NAME))
//                        .internationalName(rs.getString(INTERNATIONAL_NAME))
//                        .medicineForm(rs.getString(FORM))
//                        .medicineDose(rs.getInt(DOSE))
//                        .productArrival(rs.getDouble(PRODUCT_ARRIVAL))
//                        .diseaseGroup(DiseaseGroup.valueOf(rs.getString(DISEASE_GROUP)))
//                        .arrivalDate(rs.getDate(ARRIVAL_DATE))
//                        .invoiceNumber(rs.getString(INVOICE_NUMBER))
//                        .medicineExpirationDate(rs.getDate(EXPIRATION_DATE))
//                        .isPrescriptionRequired(rs.getBoolean(PRESCRIPTION_REQUIRED))
//                        .packagePrice(rs.getDouble(PRICE))
//                        .producer(getProducerById(rs.getInt(PRODUCER_ID)))
//                        .serialNumber(rs.getString(SERIAL_NUMBER))
//                        .productBalance(rs.getDouble(PRODUCT_BALANCE))
//                        .build();
//
//                medicines.add(medicine);
//            }
//
//        } catch (SQLException e) {
//            throw new DAOException("Error during returning medicines list", e);
//        }
//        finally {
//            ConnectionPool.getInstance().closeConnection(connection, statement);
//        }
        return null;
    }

    @Override
    public void updateMedicine(Medicine medicine) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_MEDICINE_SQL);
            statement.setDouble(1, medicine.getProductArrival());
            statement.setDouble(2, medicine.getProductBalance());
            statement.setString(3, medicine.getInvoiceNumber());
            statement.setDate(4, new java.sql.Date(medicine.getArrivalDate().getTime()));
            statement.setDouble(5, medicine.getPackagePrice());
            statement.setString(6, medicine.getSerialNumber());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during updating medicine", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }
}
