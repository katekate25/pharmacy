package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Order;
import com.epam.training.epharmacy.entity.OrderEntry;
import com.epam.training.epharmacy.entity.User;

public interface OrderService {

    Order getCartForUser(User user);

    OrderEntry addOrUpdateOrderEntry(String serialNumber, Double amount, User client);

}
