package com.chainhaus.mtg.exception.handler;

import com.chainhaus.mtg.exception.exception.FieldValueException;
import com.chainhaus.mtg.exception.exception.IPFSException;
import com.chainhaus.mtg.exception.exception.RegistrationException;
import com.chainhaus.mtg.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({RegistrationException.class})
    public ResponseEntity registrationHandler(Exception ex, WebRequest request){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getMessage(), ex.getMessage()));
    }

    @ExceptionHandler({FieldValueException.class})
    public ResponseEntity fieldValueExceptionHandler(FieldValueException ex, WebRequest request){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getReason(), ex.getFieldName()));
    }

    @ExceptionHandler({IllegalAccessException.class})
    public ResponseEntity fieldValueExceptionHandler(IllegalAccessException ex, WebRequest request){
        return ResponseEntity.badRequest().body(new ExceptionResponse("UN-AUTHORIZED", ex.getMessage()));
    }

    @ExceptionHandler({IPFSException.class})
    public ResponseEntity ipfsExceptionHandler(Exception ex){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getMessage(), "Check properties and configured IPFS node!"));
    }


}
