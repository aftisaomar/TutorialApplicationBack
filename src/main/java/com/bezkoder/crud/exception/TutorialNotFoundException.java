package com.bezkoder.crud.exception;

public class TutorialNotFoundException extends RuntimeException {

    public String message;

    public TutorialNotFoundException(String message){
        this.message = message;
    }

}
