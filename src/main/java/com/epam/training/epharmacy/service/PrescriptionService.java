package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.OrderStatus;
import com.epam.training.epharmacy.entity.Prescription;
import com.epam.training.epharmacy.entity.PrescriptionStatus;
import com.epam.training.epharmacy.entity.Producer;
import com.epam.training.epharmacy.entity.User;

import java.util.Date;
import java.util.List;

public interface PrescriptionService {

    void addPrescription(Prescription prescription);

    public Medicine getMedicineByNameSerialNumber(String serialNumber);

    public User getUserByLogin(String login);

    public List<Prescription> showPrescription(String login);

    public List<Prescription> getAllPrescription();

    void updatePrescription(Integer prescriptionNumber, PrescriptionStatus status);

    List<Prescription> findValidPrescriptionsByMedicine(User currentUser, Date currentDate, String medicineSerialNumber);

}
