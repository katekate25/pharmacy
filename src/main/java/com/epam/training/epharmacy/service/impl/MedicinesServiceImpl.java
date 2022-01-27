package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.ProducerDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MedicinesServiceImpl implements MedicinesService {
    private final Logger LOG = LogManager.getLogger(MedicinesServiceImpl.class);
    private final MedicineDAO medicineDAO = DAOFactory.getInstance().getMedicineDAO();
    private final ProducerDAO producerDAO = DAOFactory.getInstance().getProducerDAO();

    @Override
    public List<Medicine> findMedicine(String name) {
        List<Medicine> medicines = null;

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.COMMERCIAL_NAME, name);
            medicines = medicineDAO.findMedicineByCriteria(criteria);
        } catch (DAOException e){
            LOG.error("Error during finding medicine", e);
            throw  new ServiceException(e);
        }
        return medicines;
    }

    @Override
    public List<Medicine> findMedicineAnalogue(String name) {
        List<Medicine> medicines = null;
        String medicineInternationalName = findMedicine(name).get(0).getInternationalName();

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.INTERNATIONAL_NAME, medicineInternationalName);
            medicines = CollectionUtils.emptyIfNull(medicineDAO.findMedicineByCriteria(criteria))
                    .stream()
                    .filter(medicine -> !medicine.getInternationalName().equals(name))
                    .collect(Collectors.toList());
        } catch (DAOException e){
            LOG.error("Error during finding medicine analogues", e);
            throw new ServiceException(e);
        }
        return medicines;
    }

    @Override
    public void addMedicine(Medicine medicine){
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
            LOG.error("Error during adding medicine to db", e);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Medicine> showMedicineList() {
        try {
            return medicineDAO.findMedicineByCriteria(new Criteria<>());

        } catch (DAOException e){
            LOG.error("Error during returning medicine list", e);
            throw  new ServiceException(e);
        }
    }

    @Override
    public Medicine findMedicineToShow(String serialNumber) {
        Medicine medicine = null;
        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, serialNumber);
            medicine = medicineDAO.findMedicineByCriteria(criteria).iterator().next();
        } catch (DAOException e){
            LOG.error("Error during searching medicines to show", e);
            throw  new ServiceException(e);
        }
        return medicine;
    }

    @Override
    public Producer getProducerByName(String name) {
        List<Producer> producers = null;
        try {
            Criteria<SearchCriteria.Producer> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Producer.FACTORY_NAME, name);
            producers = producerDAO.findProducerByCriteria(criteria);
            if (CollectionUtils.isNotEmpty(producers)) {
               return producers.iterator().next();
            }
        } catch (DAOException | SQLException e){
            LOG.error("Error during searching producer by name", e);
            throw new ServiceException(e);
        }
        return null;
    }
}
