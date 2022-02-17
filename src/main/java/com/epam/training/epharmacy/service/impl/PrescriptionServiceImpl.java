package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.PrescriptionDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.PrescriptionService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
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
            prescription.setStatus(PrescriptionStatus.NOT_USED);
            prescriptionDAO.addPrescription(prescription);
        } catch (DAOException e) {
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
        if (CollectionUtils.isEmpty(medicines)) {
            throw new ServiceException(String.format("There is no medicine with serialNumber %s", serialNumber));
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
        if (CollectionUtils.isEmpty(users)) {
            throw new ServiceException(String.format("There is no user with login %s", login));
        }
        return users.iterator().next();
    }

    @Override
    public List<Prescription> getUserAvailablePrescriptions(int userId) {
        try {
            Criteria<SearchCriteria.Prescription> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Prescription.CLIENT_ID, userId);
            criteria.getParametersMap().put(SearchCriteria.Prescription.STATUS, PrescriptionStatus.NOT_USED);
            return prescriptionDAO.findPrescriptionByCriteria(criteria);

        } catch (DAOException e){
            LOG.error("Error during finding prescriptions for customer", e);
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> getAllPrescription() {
        try {
            return prescriptionDAO.findPrescriptionByCriteria(new Criteria<>());
        } catch (DAOException e){
            LOG.error("Error during finding prescriptions list", e);
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> getAllUserPrescription(String login) {
        List<Prescription> prescriptions = null;
        try {
            Criteria<SearchCriteria.User> criteriaUser = new Criteria<>();
            criteriaUser.getParametersMap().put(SearchCriteria.User.LOGIN, login);

            Criteria<SearchCriteria.Prescription> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Prescription.CLIENT_ID, userDAO.findUserByCriteria(criteriaUser).iterator().next().getId());
            prescriptions = prescriptionDAO.findPrescriptionByCriteria(criteria);
        } catch (DAOException e){
            LOG.error("Error during finding prescriptions list", e);
            throw  new ServiceException(e);
        }
        return prescriptions;
    }

    @Override
    public void updatePrescription(Integer prescriptionNumber, PrescriptionStatus status) {
        Criteria<SearchCriteria.Prescription> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Prescription.PRESCRIPTION_NUMBER, prescriptionNumber);
        Prescription prescription = prescriptionDAO.findPrescriptionByCriteria(criteria).iterator().next();

        try {
            prescription.setStatus(status);
            prescriptionDAO.updatePrescription(prescription);
        } catch (DAOException e) {
            LOG.error("Error during prescription update", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> findValidPrescriptionsByMedicine(User currentUser, String medicineSerialNumber) {
        Integer medicineId = getMedicineByNameSerialNumber(medicineSerialNumber).getId();
        Date currentDate = new Date();
        List<Prescription> validPrescriptions = new ArrayList<>();
        try {
            Criteria<SearchCriteria.Prescription> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Prescription.MEDICINES_ID, medicineId);
            List<Prescription> prescriptions = prescriptionDAO.findPrescriptionByCriteria(criteria);
            for (Prescription prescription : prescriptions) {
                if ((prescription.getExpirationDate().after(currentDate)
                            || prescription.getExpirationDate().compareTo(currentDate) == 0)
                        && (prescription.getCreationDate().before(currentDate)
                            || prescription.getCreationDate().compareTo(currentDate) == 0)
                        && prescription.getClient().getLogin().equals(currentUser.getLogin())){
                    validPrescriptions.add(prescription);
                }
            }

        } catch (DAOException e) {
            LOG.error("Error during finding prescription by medicine serial number", e);
            throw new ServiceException(e);
        }
        return validPrescriptions;
    }


}
