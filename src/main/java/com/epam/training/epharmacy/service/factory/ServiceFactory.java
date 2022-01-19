package com.epam.training.epharmacy.service.factory;

import com.epam.training.epharmacy.service.*;
import com.epam.training.epharmacy.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final MedicinesService medicinesService = new MedicinesServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final MessageService messageService = new MessageServiceImpl();
    private final PrescriptionService prescriptionService = new PrescriptionServiceImpl();


    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public MedicinesService getMedicinesService() {
        return medicinesService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public PrescriptionService getPrescriptionService() {
        return prescriptionService;
    }
}
