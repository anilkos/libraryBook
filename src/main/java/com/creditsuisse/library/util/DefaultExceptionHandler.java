package com.creditsuisse.library.util;

import com.creditsuisse.library.exception.BookNotFoundException;
import com.creditsuisse.library.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<Object> handleBookNotFoundException
            (Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleDefaultException
            (Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);    }
}
