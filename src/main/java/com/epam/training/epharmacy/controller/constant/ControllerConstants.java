package com.epam.training.epharmacy.controller.constant;

public class ControllerConstants {

    public static final String PHARMACY_CONTROLLER = "/pharmacy/controller?";
    public static final String ERROR_PAGE = PHARMACY_CONTROLLER + "command=GO_TO_ERROR_PAGE";
    public static final String GREETING_PAGE = PHARMACY_CONTROLLER + "command=GO_TO_GREETING_PAGE";


    public static final String DATE_FORMAT_PATTERN = "dd-MM-yyyy";

    private ControllerConstants() {
    }
}
