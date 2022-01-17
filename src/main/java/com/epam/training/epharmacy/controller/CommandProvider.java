package com.epam.training.epharmacy.controller;

import com.epam.training.epharmacy.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();
    public CommandProvider(){
        commands.put("Login", new LoginCommand());
        commands.put("Registration", new RegistrationCommand());
        commands.put("localization", new LocalizationCommand());


        commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
        commands.put("GO_TO_LOGIN_PAGE", new GoToLoginPageCommand());
        commands.put("GO_TO_GREETING_PAGE", new GoToGreetingPage());
        commands.put("GO_TO_MAIN_PAGE", new GoToMainPage());
        commands.put("GO_TO_CATALOG", new GoToCatalogPage());
        commands.put("GO_TO_PERSONAL_CABINET", new GoToPersonalCabinet());
        commands.put("SHOW_MEDICINES_LIST", new ShowMedicinesListCommand());
        commands.put("SEARCH_MEDICINES", new SearchMedicineCommand());
        commands.put("Logout", new LogoutCommand());
        commands.put("SHOW_ORDERS", new ShowOrdersCommand());
        commands.put("SHOW_MEDICINE_DETAILS", new ShowMedicineDetailsCommand());
        commands.put("GO_TO_MEDICINE_PAGE", new GoToMedicinePage());
        commands.put("CHOOSE_AMOUNT", new ChooseMedicineAmountCommand());
        commands.put("ADD_ENTRY_TO_CART", new AddEntryToCartCommand());
        commands.put("GO_TO_ERROR_PAGE", new GoToNotFoundErrorPage());
        commands.put("ADD_MEDICINE_TO_DB", new AddMedicineToDatabaseCommand());
        commands.put("GO_TO_CART", new GoToCartCommand());
        commands.put("GO_TO_MEDICINE_FORM", new GoToMedicineFormCommand());
        commands.put("GO_TO_PRESCRIPTION_PAGE", new GoToPrescriptionFormPage());
        commands.put("ADD_PRESCRIPTION_TO_DB", new AddPrescriptionCommand());
        commands.put("SHOW_DOCTORS", new ShowAllDoctorsCommand());
        commands.put("GO_TO_DOCTOR_CATALOG", new GoToDoctorsCatalogPageCommand());
        commands.put("GO_TO_DOCTOR_PERSONAL_PAGE", new GoToDoctorPersonalPage());
        commands.put("CREATE_REQUEST_FOR_PRESCRIPTION", new CreateRequestForPrescriptionCommand());
        commands.put("SHOW_TOTAL_PRICE", new ShowTotalPriceCommand());
        commands.put("ADD_ORDER", new AddOrderCommand());
    }

    public final Command getCommand(String commandName){
        Command command = commands.get(commandName);
        return command;
    }
}
