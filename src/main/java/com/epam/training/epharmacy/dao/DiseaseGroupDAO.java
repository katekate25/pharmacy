package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.DiseaseGroup;
import com.epam.training.epharmacy.entity.SearchCriteria;

import java.sql.SQLException;
import java.util.List;

public interface DiseaseGroupDAO {
    void addDiseaseGroup (DiseaseGroup diseaseGroup) throws DAOException, SQLException;

    List<DiseaseGroup> findDiseaseGroupByCriteria(Criteria<SearchCriteria.DiseaseGroup> criteria) throws DAOException;
}
