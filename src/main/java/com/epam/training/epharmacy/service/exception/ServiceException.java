package com.epam.training.epharmacy.service.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
