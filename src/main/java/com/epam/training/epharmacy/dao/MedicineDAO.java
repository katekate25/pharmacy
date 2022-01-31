package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.SearchCriteria;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDAO {

    List<Medicine> findMedicineByCriteria(Criteria<SearchCriteria.Medicine> criteria) throws DAOException;

    List<Medicine> findMedicineByCriteria(Criteria<SearchCriteria.Medicine> criteria, boolean isUseLike) throws DAOException;

    void addORUpdateMedicine(Medicine medicine) throws DAOException, SQLException;

    void addMedicine (Medicine medicine) throws DAOException, SQLException;

    void updateMedicine(Medicine medicine) throws DAOException, SQLException;

    void deleteMedicine(Medicine medicine) throws DAOException, SQLException;

}
