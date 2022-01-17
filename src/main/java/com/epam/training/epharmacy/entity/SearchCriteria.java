package com.epam.training.epharmacy.entity;

public final class SearchCriteria {

    public static enum Medicine {
        ID,
        COMMERCIAL_NAME,
        INTERNATIONAL_NAME,
        SERIAL_NUMBER,
        PRICE,
        EXPIRATION_DATE,
        INVOICE_NUMBER,
        PRESCRIPTION_REQUIRED,
        PRODUCER,
        DISEASE_GROUP
    }

    public static enum Prescription {
        PRESCRIPTION_NUMBER,
        CLIENT_ID,
        DOCTOR_ID,
        CREATION_DATE,
        EXPIRATION_DATE,
        MEDICINES_ID,
        NUMBER_OF_PACKAGES
    }

    public static enum Order {
        NUMBER,
        CLIENTS_ID,
        DELIVERY_TIME,
        PHARMACISTS_ID,
        ORDER_STATUS,
        PAYMENT_STATUS
    }

    public static enum OrderEntry{
        ID,
        PACKAGE_AMOUNT,
        ORDER_NUMBER,
        MEDICINES_ID
    }

    public static enum User {
        ID, NAME, ROLES_CODE, LOGIN, PASSWORD
    }

    public static enum Message {
        ID, MESSAGE, DATE, RECIPIENT, SENDER
    }

    public static enum UserRole {
        CODE
    }

    public static enum Producer {
        ID, FACTORY_NAME, PRODUCER_COUNTRY
    }

    public static enum DiseaseGroup {
        CODE
    }

    private SearchCriteria() {
    }
}
