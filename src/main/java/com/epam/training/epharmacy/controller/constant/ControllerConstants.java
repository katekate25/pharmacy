package com.epam.training.epharmacy.controller.constant;

public class ControllerConstants {

    public static final String UTF_8 = "UTF-8";
    public static final String PHARMACY_CONTROLLER = "/pharmacy/controller?";
    public static final String ERROR_PAGE = PHARMACY_CONTROLLER + "command=GO_TO_ERROR_PAGE";
    public static final String GREETING_PAGE = PHARMACY_CONTROLLER + "command=GO_TO_GREETING_PAGE";
    public static final String LOGIN_PAGE = PHARMACY_CONTROLLER + "command=GO_TO_LOGIN_PAGE";
    public static final String PERSONAL_CABINET = PHARMACY_CONTROLLER + "command=GO_TO_PERSONAL_CABINET";
    public static final String ORDERS_PAGE = PHARMACY_CONTROLLER + "command=GO_TO_ORDERS";
    public static final String DOCTOR_PAGE = PHARMACY_CONTROLLER + "command=GO_TO_DOCTOR_PERSONAL_PAGE";
    public static final String ORDER_LIST = PHARMACY_CONTROLLER + "command=GO_TO_ORDER_LIST";


    public static final String DATE_FORMAT_PATTERN = "dd-MM-yyyy";

    private ControllerConstants() {
    }
}
