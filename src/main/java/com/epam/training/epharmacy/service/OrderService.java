package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Order;
import com.epam.training.epharmacy.entity.OrderEntry;
import com.epam.training.epharmacy.entity.OrderStatus;
import com.epam.training.epharmacy.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface OrderService {

    Order getCartForUser(User user);

    OrderEntry addOrUpdateOrderEntry(String serialNumber, Double amount, User client);

    void deleteOrderEntry(User user,int id);

    void payOrder(User user, Date date);

    List<Order> getOrdersForUser(User user);

    List<Order> getOrdersForAdmin();

    void updateOrder(Integer orderNumber, OrderStatus status) throws SQLException;

    void addPrescriptionToEntry(Integer orderEntryNumber, Integer prescrNumber) throws SQLException;
}
