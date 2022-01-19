package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.controller.impl.AddPrescriptionCommand;
import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.PrescriptionDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.PrescriptionService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService {

    private final Logger LOG = LogManager.getLogger(PrescriptionServiceImpl.class);

    private final DAOFactory factory = DAOFactory.getInstance();
    private final MedicineDAO medicineDAO = factory.getMedicineDAO();
    private final PrescriptionDAO prescriptionDAO = factory.getPrescriptionDAO();
    private final UserDAO userDAO = factory.getUserDAO();

    @Override
    public void addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
        } catch (DAOException | SQLException e) {
            LOG.error("Error during adding prescription", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Medicine getMedicineByNameSerialNumber(String serialNumber) {
        List<Medicine> medicines = null;
        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, serialNumber);
            medicines = medicineDAO.findMedicineByCriteria(criteria);
        } catch (DAOException e) {
            LOG.error("Error during finding medicine by serial number", e);
            throw new ServiceException(e);
        }
        return medicines.iterator().next();
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> users = null;
        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
            users = userDAO.findUserByCriteria(criteria);
        } catch (DAOException e) {
            LOG.error("Error during finding user by login", e);
            throw new ServiceException(e);
        }
        return users.iterator().next();
    }
}
