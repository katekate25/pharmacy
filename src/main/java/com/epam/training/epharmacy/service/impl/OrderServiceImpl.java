package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MedicineDAO;
import com.epam.training.epharmacy.dao.OrderDAO;
import com.epam.training.epharmacy.dao.OrderEntryDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

import static com.epam.training.epharmacy.entity.OrderStatus.READY_FOR_PAYMENT;

public class OrderServiceImpl implements OrderService {

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
            // TODO log exception
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
            }
            else {
                if (order == null) {
                    order = createNewOrder(user);
                }
                orderEntry = createNewOrderEntry(amount, medicine, order);
            }
            medicine.setProductBalance(medicine.getProductBalance() - amount);
            medicineDAO.updateMedicine(medicine);
            recalculateCart(user);
        } catch (DAOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return orderEntry;
    }

    private void recalculateCart(User user) {
        Order cart = getCartForUser(user);
        double totalPrice = 0.0;
        if(cart != null && cart.getOrderEntries() != null) {
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
        if (order != null && order.getOrderEntries() != null)
        {
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
