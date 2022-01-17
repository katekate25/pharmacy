package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.Producer;

import java.util.List;

public interface MedicinesService {

    public List<Medicine> findMedicine(String name);

    public List<Medicine> findMedicineAnalogue(String name);

    public void addMedicine(Medicine medicine);

    public List<Medicine> showMedicineList();

    public Medicine findMedicineToShow(String serialNumber);

    Producer getProducerByName(String name);
}
