package com.bezkoder.crud.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TutorialExceptionController {


    @ExceptionHandler (value=TutorialNotFoundException.class)
    public ResponseEntity<ErrorMessage> tutorialNotFoundException(TutorialNotFoundException ex, WebRequest request){
        
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(),new Date(), ex.getMessage(), request.getDescription(false));
        
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);

    }

}
