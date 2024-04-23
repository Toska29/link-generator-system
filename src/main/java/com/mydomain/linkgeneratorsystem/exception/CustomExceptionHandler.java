package com.mydomain.linkgeneratorsystem.exception;

import com.mydomain.linkgeneratorsystem.exception.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LinkGeneratorException.class)
    public ResponseEntity<?> handleLinkGeneratorException(LinkGeneratorException linkGeneratorException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(linkGeneratorException.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
