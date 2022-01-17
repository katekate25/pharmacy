package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.PrescriptionDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.PrescriptionService;
import com.epam.training.epharmacy.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService {
    @Override
    public void addPrescription(Prescription prescription) {
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();
        PrescriptionDAO prescriptionDAO = factory.getPrescriptionDAO();

        try {
                prescriptionDAO.addPrescription(prescription);
            }

     catch (DAOException | SQLException e){
            //logger.error("Problems while adding prescription to db", e);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Medicine getMedicineByNameSerialNumber(String serialNumber) {
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();
        List<Medicine> medicines = null;

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, serialNumber);
            medicines = medicineDAO.findMedicineByCriteria(criteria);
        } catch (DAOException e){
            throw  new ServiceException(e);
        }
        return medicines.iterator().next();
    }

    @Override
    public User getUserByLogin(String login) {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        List<User> users = null;

        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
            users = userDAO.findUserByCriteria(criteria);
        } catch (DAOException e){
            throw  new ServiceException(e);
        }
        return users.iterator().next();
    }
}
