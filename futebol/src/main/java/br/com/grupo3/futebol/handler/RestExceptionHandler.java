package br.com.grupo3.futebol.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.grupo3.futebol.common.dateConverter;
import br.com.grupo3.futebol.model.error.ErrorResponse;
import br.com.grupo3.futebol.model.exceptions.ResourceBadRequest;
import br.com.grupo3.futebol.model.exceptions.ResourceNotFound;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceBadRequest.class)
    public ResponseEntity<ErrorResponse> handlerBadRequest(ResourceBadRequest badRequest) {

        ErrorResponse errorMessage = new ErrorResponse(400,
                "Bad Request", badRequest.getMessage(), dateConverter.converter(new Date()));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(ResourceNotFound notFound) {

        ErrorResponse errorMessage = new ErrorResponse(404,
                "Not Found", notFound.getMessage(), dateConverter.converter(new Date()));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception exception) {

        ErrorResponse errorMessage = new ErrorResponse(500,
                "Internal Server Error", exception.getMessage(), dateConverter.converter(new Date()));
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

