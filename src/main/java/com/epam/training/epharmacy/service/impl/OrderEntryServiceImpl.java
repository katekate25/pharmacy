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

    private final OrderEntryDAO orderEntryDAO = DAOFactory.getInstance().getOrderEntryDAO();
    private final MedicineDAO medicineDAO = DAOFactory.getInstance().getMedicineDAO();
    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    @Override
    public OrderEntry addOrderEntry(String serialNumber, Double amount, User user) {

        OrderEntry orderEntry = new OrderEntry();
        List<Medicine> medicines = null;
        List<Order> orders = null;
        String str = new ThreadLocal<String>().get();
        try {

            Criteria<SearchCriteria.Medicine> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.Medicine.SERIAL_NUMBER, serialNumber);
            medicines = medicineDAO.findMedicineByCriteria(criteria);

            orderEntry.setMedicine(medicines.iterator().next());
            orderEntry.setPackageAmount(amount);

            Criteria<SearchCriteria.Order> criteriaOrd = new Criteria<>();
            criteriaOrd.getParametersMap().put(SearchCriteria.Order.ORDER_STATUS, READY_FOR_PAYMENT);
            criteriaOrd.getParametersMap().put(SearchCriteria.Order.CLIENTS_ID, user.getId());
            orders = orderDAO.findOrderByCriteria(criteriaOrd);
            if (orders != null && !orders.isEmpty() ) {
                orderEntry.setOrderNumber(orders.iterator().next().getOrderNumber());
            } else {
                Order order = new Order();
                order.setClient(user);
                order.setOrderStatus(READY_FOR_PAYMENT);
                orderDAO.saveOrder(order);

                orderEntry.setOrderNumber(order.getOrderNumber());
            }
            orderEntryDAO.saveOrderEntry(orderEntry);

            double newBalance = medicines.iterator().next().getProductBalance() - amount;
            medicines.iterator().next().setProductBalance(newBalance);
            medicineDAO.updateMedicine(medicines.iterator().next());

        } catch (DAOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return orderEntry;
    }

    @Override
    public List<OrderEntry> showEntries() {
        DAOFactory factory = DAOFactory.getInstance();
        MedicineDAO medicineDAO = factory.getMedicineDAO();
        OrderEntryDAO orderEntryDAO = factory.getOrderEntryDAO();
        try {

          return orderEntryDAO.showEntries();

        } catch (DAOException | SQLException e){
            // TODO log exception
            throw  new ServiceException(e);
        }
    }


    @Override
    public void deleteOrderEntry(OrderEntry orderEntry) {

    }

    @Override
    public double getTotalPrice() {
        Double sum = 0.0;
        List<OrderEntry> orderEntries = null;

        orderEntries = showEntries();
        for (OrderEntry entry : orderEntries){
            sum =+ (entry.getMedicine().getPackagePrice() * entry.getPackageAmount());
        }

        return sum;
    }
}

