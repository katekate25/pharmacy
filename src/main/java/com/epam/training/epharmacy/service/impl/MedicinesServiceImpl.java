package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.ProducerDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MedicinesServiceImpl implements MedicinesService {
    Logger logger = LogManager.getLogger(MedicinesServiceImpl.class);

    @Override
    public List<Medicine> findMedicine(String name) {
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();
        List<Medicine> medicines = null;

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.COMMERCIAL_NAME, name);
            medicines = medicineDAO.findMedicineByCriteria(criteria);
        } catch (DAOException e){
            throw  new ServiceException(e);
        }
        return medicines;
    }

    @Override
    public List<Medicine> findMedicineAnalogue(String name) {
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();
        List<Medicine> medicines = null;
        String medicineInternatName = findMedicine(name).get(0).getInternationalName();

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.INTERNATIONAL_NAME, medicineInternatName);
            medicines = medicineDAO.findMedicineByCriteria(criteria);

            if (medicines != null && !medicines.isEmpty()) {
                medicines.iterator().next();
            }
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return medicines;
    }

    @Override
    public void addMedicine(Medicine medicine){
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, medicine.getSerialNumber());
            List<Medicine> medicines = medicineDAO.findMedicineByCriteria(criteria);
            if (medicines != null && !medicines.isEmpty()) {
                Medicine medicineExist = medicines.iterator().next();
                medicineExist.setProductArrival(medicine.getProductArrival() + medicineExist.getProductArrival());
                medicineExist.setProductBalance(medicine.getProductArrival() + medicineExist.getProductBalance());
                medicineExist.setInvoiceNumber(medicine.getInvoiceNumber());
                medicineExist.setArrivalDate(medicine.getArrivalDate());
                medicineExist.setPackagePrice(medicine.getPackagePrice());

                medicineDAO.updateMedicine(medicineExist);
            }else {
                medicineDAO.addMedicine(medicine);
            }
        } catch (DAOException | SQLException e){
            logger.error("Problems while adding medicine to db", e);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Medicine> showMedicineList() {
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();
        try {
            return medicineDAO.showMedicineList();

        } catch (DAOException | SQLException e){
            // TODO log exception
            throw  new ServiceException(e);
        }
    }

    @Override
    public Medicine findMedicineToShow(String serialNumber) {
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();
        Medicine medicine = null;

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, serialNumber);
            medicine = medicineDAO.findMedicineByCriteria(criteria).iterator().next();
        } catch (DAOException e){
            throw  new ServiceException(e);
        }
        return medicine;
    }

    @Override
    public Producer getProducerByName(String name) {
        DAOFactory factory = DAOFactory.getInstance();
        ProducerDAO producerDAO = factory.getProducerDAO();
        List<Producer> producers = null;

        try {
            Criteria<SearchCriteria.Producer> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Producer.FACTORY_NAME, name);
            producers = producerDAO.findProducerByCriteria(criteria);
        } catch (DAOException | SQLException e){
            throw  new ServiceException(e);
        }
        return producers.iterator().next();

    }
}
