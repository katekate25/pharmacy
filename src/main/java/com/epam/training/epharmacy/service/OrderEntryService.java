package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.OrderEntry;
import com.epam.training.epharmacy.entity.User;

import java.util.List;

public interface OrderEntryService {

    OrderEntry addOrderEntry (String serialNumber, Double amount, User client);

    List<OrderEntry> showEntries();

    void deleteOrderEntry (OrderEntry orderEntry);

    double getTotalPrice ();

}
