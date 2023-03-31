package com.bezkoder.crud.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class TutorialExceptionController {


    @ExceptionHandler (value=TutorialNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage tutorialNotFoundException(TutorialNotFoundException ex, WebRequest request){

        System.out.println("error message is : "+ex.getMessage());
        
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(),new Date(), ex.getMessage(), request.getDescription(false));
        
        return errorMessage;

    }

}
