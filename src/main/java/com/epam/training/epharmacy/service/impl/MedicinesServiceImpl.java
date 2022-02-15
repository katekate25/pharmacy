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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicinesServiceImpl implements MedicinesService {
    private final Logger LOG = LogManager.getLogger(MedicinesServiceImpl.class);
    private final MedicineDAO medicineDAO = DAOFactory.getInstance().getMedicineDAO();
    private final ProducerDAO producerDAO = DAOFactory.getInstance().getProducerDAO();


    @Override
    public List<Medicine> findMedicine(String name, boolean isFindByPartialName) {
        List<Medicine> medicines = null;

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.COMMERCIAL_NAME, name);
            medicines = isFindByPartialName ?
                    medicineDAO.findMedicineByCriteria(criteria, isFindByPartialName) :
                    medicineDAO.findMedicineByCriteria(criteria);
        } catch (DAOException e){
            LOG.error("Error during finding medicine", e);
            throw  new ServiceException(e);
        }
        return medicines;
    }

    @Override
    public List<Medicine> findMedicineAnalogue(Medicine medicine) {
        List<Medicine> analogues;

        try {
            if (medicine == null)
            {
                return Collections.emptyList();
            }
            String medicineInternationalName = medicine.getInternationalName();
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.INTERNATIONAL_NAME, medicineInternationalName);
            analogues = CollectionUtils.emptyIfNull(medicineDAO.findMedicineByCriteria(criteria))
                    .stream()
                    .filter(analogue -> !analogue.getCommercialName().equals(medicine.getCommercialName()))
                    .collect(Collectors.toList());
        } catch (DAOException e){
            LOG.error("Error during finding medicine analogues", e);
            throw new ServiceException(e);
        }
        return analogues;
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
    public List<Medicine> findAllMedicines() {
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

    @Override
    public List<Medicine> medicineByIncome(Date beginning, Date end) {
        List<Medicine> medicines = null;
        List<Medicine> foundMedicines = null;

        try {
            medicines = medicineDAO.findMedicineByCriteria(new Criteria<>());
            for (Medicine medicine:medicines) {
                if ((medicine.getArrivalDate().after(beginning) || medicine.getArrivalDate().equals(beginning))
                        && (medicine.getArrivalDate().before(end) || medicine.getArrivalDate().equals(end))  ){
                    foundMedicines.add(medicine);
                }
            }

        } catch (DAOException e){
            LOG.error("Error during finding medicines by date", e);
            throw  new ServiceException(e);
        }
        return foundMedicines;
    }

    @Override
    public Set<String> showInvoiceList(Date beginning, Date end) {
        List<Medicine> medicines = medicineByIncome(beginning, end);
        Set<String> invoices = null;
        try {
            for (Medicine medicine : medicines){
                invoices.add(medicine.getInvoiceNumber());
            }

        } catch (DAOException e){
            LOG.error("Error during finding medicines by invoice", e);
            throw  new ServiceException(e);
        }
        return invoices;
    }

    @Override
    public List<Medicine> medicineByInvoiceNumber(String invoiceNumber) {
        List<Medicine> medicines = null;

        try {
            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.INVOICE_NUMBER, invoiceNumber);
            medicines = medicineDAO.findMedicineByCriteria(criteria);

        } catch (DAOException e){
            LOG.error("Error during finding medicines by date", e);
            throw  new ServiceException(e);
        }
        return medicines;
    }
}
