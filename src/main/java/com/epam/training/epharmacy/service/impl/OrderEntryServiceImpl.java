package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.OrderDAO;
import com.epam.training.epharmacy.dao.OrderEntryDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.OrderEntryService;
import com.epam.training.epharmacy.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

import static com.epam.training.epharmacy.entity.OrderStatus.READY_FOR_PAYMENT;

public class OrderEntryServiceImpl implements OrderEntryService {


    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();



    @Override
    public void deleteOrderEntry(OrderEntry orderEntry) {

    }

//    @Override
//    public double getTotalPrice() {
//        Double sum = 0.0;
//        List<OrderEntry> orderEntries = null;
//
//        orderEntries = getOrderEntriesForUser();
//        for (OrderEntry entry : orderEntries) {
//            sum = +(entry.getMedicine().getPackagePrice() * entry.getPackageAmount());
//        }
//
//        return sum;
//    }
}

