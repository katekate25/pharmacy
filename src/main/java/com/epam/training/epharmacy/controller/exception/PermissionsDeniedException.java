package com.epam.training.epharmacy.controller.exception;

public class PermissionsDeniedException extends RuntimeException {

    public PermissionsDeniedException (){
        super();
    }

    public PermissionsDeniedException (Exception e){
        super(e);
    }

    public PermissionsDeniedException (String message, Exception e){
        super(message, e);
    }
}
