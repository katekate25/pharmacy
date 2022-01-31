package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.OrderDAO;
import com.epam.training.epharmacy.dao.OrderEntryDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.epam.training.epharmacy.entity.OrderStatus.READY_FOR_PAYMENT;

public class OrderServiceImpl implements OrderService {

    private final Logger LOG = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
    private final OrderEntryDAO orderEntryDAO = DAOFactory.getInstance().getOrderEntryDAO();
    private final MedicineDAO medicineDAO = DAOFactory.getInstance().getMedicineDAO();

    @Override
    public Order getCartForUser(User user) {
        Criteria<SearchCriteria.Order> criteriaOrd = new Criteria<>();
        criteriaOrd.getParametersMap().put(SearchCriteria.Order.ORDER_STATUS, READY_FOR_PAYMENT);
        criteriaOrd.getParametersMap().put(SearchCriteria.Order.CLIENTS_ID, user.getId());
        try {
            List<Order> orders = orderDAO.findOrderByCriteria(criteriaOrd);
            return orders != null && !orders.isEmpty() ? orders.iterator().next() : null;
        } catch (DAOException | SQLException e) {
            LOG.error("Error during getting cart for user", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderEntry addOrUpdateOrderEntry(String serialNumber, Double amount, User user) {
        OrderEntry orderEntry;
        try {
            Medicine medicine = findMedicineBySerialNumber(serialNumber);
            Order order = getCartForUser(user);
            if (isOrderAlreadyHasAppropriateOrderEntry(order, serialNumber)) {
                orderEntry = updateExistOrderEntry(serialNumber, amount, order);
            } else {
                if (order == null) {
                    order = createNewOrder(user);
                }
                orderEntry = createNewOrderEntry(amount, medicine, order);
            }
            medicine.setProductBalance(medicine.getProductBalance() - amount);
            medicineDAO.updateMedicine(medicine);
            recalculateCart(user);
        } catch (DAOException | SQLException e) {
            LOG.error("Error during adding or updating entry", e);
            throw new IllegalArgumentException(e);
        }
        return orderEntry;
    }

    @Override
    public void deleteOrderEntry(User user, int id) {
        Criteria<SearchCriteria.OrderEntry> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.OrderEntry.ID, id);
        try {
            List<OrderEntry> entries = orderEntryDAO.findOrderEntryByCriteria(criteria);
            if (entries != null && !entries.isEmpty()) {
                OrderEntry entry = entries.iterator().next();
                Medicine medicine = findMedicineBySerialNumber(entry.getMedicine().getSerialNumber());
                medicine.setProductBalance(medicine.getProductBalance() + entry.getPackageAmount());
                medicineDAO.updateMedicine(medicine);
                orderEntryDAO.deleteOrderEntry(entry);
                recalculateCart(user);
            }
        } catch (DAOException | SQLException e) {
            LOG.error("Error during deleting entry", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void payOrder(User user, Date date) {
        Order order = getCartForUser(user);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setOrderStatus(OrderStatus.PAID);
        order.setDeliveryTime(date);
        orderDAO.updateOrder(order);
    }

    @Override
    public List<Order> getOrdersForUser(User user) {
        Criteria<SearchCriteria.Order> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Order.CLIENTS_ID, user.getId());
        try {
            return orderDAO.findOrderByCriteria(criteria);
        } catch (DAOException | SQLException e) {
            LOG.error("Error during finding orders for user", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersForAdmin() {

        try {
            return orderDAO.findOrderByCriteria(new Criteria<>());
        } catch (DAOException | SQLException e) {
            LOG.error("Error during returning order list", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateOrder(Integer orderNumber, OrderStatus status) throws SQLException {
        Criteria<SearchCriteria.Order> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Order.NUMBER, orderNumber);
        Order order = orderDAO.findOrderByCriteria(criteria).iterator().next();
        try {
            order.setOrderStatus(status);
            orderDAO.updateOrder(order);
        } catch (DAOException e) {
            LOG.error("Error during order update", e);
            throw new ServiceException(e);
        }
    }

    private void recalculateCart(User user) {
        Order cart = getCartForUser(user);
        double totalPrice = 0.0;
        if (cart != null && cart.getOrderEntries() != null) {
            for (OrderEntry entry : cart.getOrderEntries()) {
                totalPrice += entry.getPackageAmount() * entry.getMedicine().getPackagePrice();
            }
            cart.setTotalPrice(totalPrice);
            orderDAO.updateOrder(cart);
        }
    }

    private OrderEntry updateExistOrderEntry(String serialNumber, Double amount, Order order) {
        OrderEntry orderEntry = getAppropriateOrderEntry(order, serialNumber);
        orderEntry.setPackageAmount(orderEntry.getPackageAmount() + amount);
        orderEntryDAO.updateOrderEntry(orderEntry);
        return orderEntry;
    }

    private OrderEntry createNewOrderEntry(Double amount, Medicine medicine, Order order) throws SQLException {
        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setMedicine(medicine);
        orderEntry.setPackageAmount(amount);
        orderEntry.setOrderNumber(order.getOrderNumber());
        orderEntryDAO.saveOrderEntry(orderEntry);
        return orderEntry;
    }

    private OrderEntry getAppropriateOrderEntry(Order order, String serialNumber) {
        if (order != null && order.getOrderEntries() != null) {
            for (OrderEntry entry : order.getOrderEntries()) {
                if (entry.getMedicine() != null && entry.getMedicine().getSerialNumber().equals(serialNumber)) {
                    return entry;
                }
            }
        }
        return null;
    }

    private boolean isOrderAlreadyHasAppropriateOrderEntry(Order order, String serialNumber) {
        return getAppropriateOrderEntry(order, serialNumber) != null;
    }

    private Medicine findMedicineBySerialNumber(String serialNumber) {
        Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, serialNumber);
        List<Medicine> medicines = medicineDAO.findMedicineByCriteria(criteria);
        if (medicines != null && !medicines.isEmpty()) {
            return medicines.iterator().next();
        }
        throw new ServiceException("There is no medicine with serialNumber=" + serialNumber);
    }

    private Order createNewOrder(User user) throws SQLException {
        Order order = new Order();
        order.setClient(user);
        order.setOrderStatus(READY_FOR_PAYMENT);
        order.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);
        orderDAO.saveOrder(order);
        return order;
    }

}