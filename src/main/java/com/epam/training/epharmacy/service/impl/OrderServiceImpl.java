package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.OrderDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> showOrdersToCustomer() {
        DAOFactory factory = DAOFactory.getInstance();
        OrderDAO orderDAO = factory.getOrderDAO();

        List<Order> orders = null;

//        try {
//           for (int i=0; i< orderDAO.showOrderList().size(); i++){
//               if (id == orderDAO.showOrderList().get(i).getClient().getId()){
//                   orders.add(showOrdersToAdmin().get(i));
//               }
//           }
//       }catch (DAOException | SQLException e){
//           // TODO log exception
//           throw  new ServiceException(e);
//       }
        return orders;
    }

    @Override
    public List<Order> showOrdersToAdmin() {
        DAOFactory factory = DAOFactory.getInstance();
        OrderDAO orderDAO = factory.getOrderDAO();

        try {
            return orderDAO.showOrderList();

        } catch (DAOException | SQLException e){
            // TODO log exception
            throw  new ServiceException(e);
        }
    }
}
