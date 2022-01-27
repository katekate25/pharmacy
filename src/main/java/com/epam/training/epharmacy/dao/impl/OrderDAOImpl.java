package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.OrderDAO;
import com.epam.training.epharmacy.dao.OrderEntryDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;

public class OrderDAOImpl extends AbstractEntityDAO implements OrderDAO {

    private static final String ADD_ORDER_SQL =
            "INSERT INTO orders (clients_id, order_status, pharmacists_id, payment_status, delivery_time, " +
                    "total_price) VALUES ((SELECT id FROM users WHERE login = ?), ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_SQL = "UPDATE orders SET delivery_time = ?, payment_status =?, " +
            "pharmacists_id=?, order_status=?, total_price=? WHERE number = ?";
    private static final String SELECT_FROM_ORDER_PREFIX = "SELECT number, clients_id, delivery_time, " +
            "pharmacists_id, order_status, payment_status, total_price FROM orders";

    private UserDAO userDAO;
    private OrderEntryDAO orderEntryDAO;

    public OrderDAOImpl(UserDAO userDAO, OrderEntryDAO orderEntryDAO) {
        this.userDAO = userDAO;
        this.orderEntryDAO = orderEntryDAO;

    }

    @Override
    protected String getInitialQuery() {
        return SELECT_FROM_ORDER_PREFIX;
    }

    @Override
    public List<Order> findOrderByCriteria(Criteria<SearchCriteria.Order> criteria) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = createStatement(connection, criteria);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Order order = new Order.Builder()
                        .orderNumber(rs.getInt(ORDER_NUMBER))
                        .client(getUserById(rs.getInt(CLIENTS_ID)))
                        .pharmacist(getUserById(rs.getInt(PHARMACISTS_ID)))
                        .deliveryTime(rs.getDate(DELIVERY_TIME))
                        .orderStatus(OrderStatus.valueOf(rs.getString(ORDER_STATUS)))
                        .paymentStatus(PaymentStatus.valueOf(rs.getString(PAYMENT_STATUS)))
                        .totalPrice(rs.getDouble(TOTAL_PRICE))
                        .build();

                Criteria<SearchCriteria.OrderEntry> orderEntriesSearchCriteria = new Criteria<>();
                orderEntriesSearchCriteria.getParametersMap()
                        .put(SearchCriteria.OrderEntry.ORDER_NUMBER, order.getOrderNumber());
                List<OrderEntry> orderEntries = orderEntryDAO.findOrderEntryByCriteria(orderEntriesSearchCriteria);
                order.setOrderEntries(orderEntries);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during finding order", e);
        }finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return orders;
    }

    private User getUserById(int userId) throws DAOException, SQLException {
        Criteria<SearchCriteria.User> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.User.ID, userId);
        List<User> users = userDAO.findUserByCriteria(criteria);
        if (!users.isEmpty()) {
            return users.iterator().next();
        }
        return null;
    }

    @Override
    public void saveOrder(Order order) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_ORDER_SQL, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getClient().getLogin());
            statement.setString(2, order.getOrderStatus() != null ? order.getOrderStatus().toString() : null);
            statement.setString(3, order.getPharmacist() != null ? order.getPharmacist().getLogin() : null);
            statement.setString(4, order.getPaymentStatus() != null ? order.getPaymentStatus().toString() : null);
            statement.setDate(5,  order.getDeliveryTime() != null ? new Date(order.getDeliveryTime().getTime()) : null);
            statement.setDouble(6, order.getTotalPrice());

            statement.executeUpdate();
            createOrderEntries(order.getOrderEntries());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding order", e);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                order.setOrderNumber(generatedKeys.getInt(1));
            }
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    private void createOrderEntries(List<OrderEntry> orderEntries) throws DAOException, SQLException {
        if (orderEntries != null) {
            for (OrderEntry orderEntry : orderEntries) {
                orderEntryDAO.saveOrderEntry(orderEntry);
            }
        }
    }

    @Override
    public void updateOrder(Order order) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_ORDER_SQL);
            statement.setDate(1, order.getDeliveryTime() != null ? new Date(order.getDeliveryTime().getTime()) : null);
            statement.setString(2, order.getPaymentStatus() != null ? order.getPaymentStatus().toString() : null);
            statement.setString(3, order.getPharmacist() != null ? order.getPharmacist().getLogin() : null);
            statement.setString(4, order.getOrderStatus() != null ? order.getOrderStatus().toString() : null);
            statement.setDouble(5, order.getTotalPrice());
            statement.setInt(6, order.getOrderNumber()!= null ? order.getOrderNumber() : null);


            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during updating order", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

}
