package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.Prescription;
import com.epam.training.epharmacy.entity.SearchCriteria;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionDAO {

    List<Prescription> findPrescriptionByCriteria(Criteria<SearchCriteria.Prescription> criteria) throws DAOException;

    void addPrescription(Prescription prescription) throws SQLException;

    void deletePrescription (int prescriptionNumber);

}
