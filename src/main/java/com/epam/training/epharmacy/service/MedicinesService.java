package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.Producer;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface MedicinesService {

    public List<Medicine> findMedicine(String name, boolean isFindByPartialName);

    public List<Medicine> findMedicineAnalogue(Medicine medicine);

    public void addMedicine(Medicine medicine);

    public List<Medicine> findAllMedicines();

    public Medicine findMedicineToShow(String serialNumber);

    Producer getProducerByName(String name);

    List <Medicine> medicineByIncome(Date beginning, Date end);

    Set<String> showInvoiceList(Date beginning, Date end);

    List <Medicine> medicineByInvoiceNumber(String invoiceNumber);
}
