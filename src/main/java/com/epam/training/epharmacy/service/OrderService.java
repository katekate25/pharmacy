package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.Order;
import com.epam.training.epharmacy.entity.User;

import java.util.List;

public interface OrderService {

    List <Order> showOrdersToCustomer();

    List <Order> showOrdersToAdmin();

}
