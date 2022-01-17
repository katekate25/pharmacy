package com.epam.training.epharmacy.dao.factory;

import com.epam.training.epharmacy.dao.*;
import com.epam.training.epharmacy.dao.impl.*;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();


    private final ProducerDAO producerDAO = new ProducerDAOImp();
    private final UserDAO userDAO = new UserDAOImpl();
    private final MedicineDAO medicineDAO = new MedicineDAOImp(producerDAO);
    private final OrderEntryDAO orderEntryDAO = new OrderEntryDAOImpl(medicineDAO);
    private final OrderDAO orderDAO = new OrderDAOImpl(userDAO, orderEntryDAO);
    private final DiseaseGroupDAO diseaseGroupDAO = new DiseaseGroupDAOImpl();

    private final PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl(medicineDAO, userDAO);
    private final MessageDAO messageDAO = new MessageDAOImpl(userDAO);

    public static DAOFactory getInstance() {
        return instance;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public MedicineDAO getMedicineDAO() {
        return medicineDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public DiseaseGroupDAO getDiseaseGroupDAO() {
        return diseaseGroupDAO;
    }

    public PrescriptionDAO getPrescriptionDAO() {
        return prescriptionDAO;
    }

    public ProducerDAO getProducerDAO() {
        return producerDAO;
    }

    public OrderEntryDAO getOrderEntryDAO() {
        return orderEntryDAO;
    }

    public MessageDAO getMessageDAO() { return  messageDAO; }
}
