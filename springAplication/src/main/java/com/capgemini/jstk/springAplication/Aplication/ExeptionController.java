package com.capgemini.jstk.springAplication.Aplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExeptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExeptionController.class);

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error businessExceptionHandler(Exception ex) {
        LOGGER.error("Error occured", ex);
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(){
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SuchUserAlreadyExistException.class)
    public ResponseEntity<Object> suchUserAlreadyExist(){
        return new ResponseEntity<>("Such user already exists", HttpStatus.FORBIDDEN);
    }
}
