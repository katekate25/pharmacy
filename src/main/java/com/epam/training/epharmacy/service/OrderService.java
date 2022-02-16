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

    List<Order> getOrdersByCustomerName(String name, boolean isFindByPartialName);

    void updateOrder(Integer orderNumber, OrderStatus status) throws SQLException;

    void addPrescriptionToEntry(Integer entryId, Integer prescriptionNumber) throws SQLException;

    boolean isCartReadyForPayment(Order order);

    void deletePrescriptionFromEntry(Integer entryId, Integer prescriptionNumber) throws SQLException;

    void updateEntryAmount(Integer entryId, Double amount, User client) throws SQLException;

}
