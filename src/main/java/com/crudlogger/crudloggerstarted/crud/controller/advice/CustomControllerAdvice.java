package com.crudlogger.crudloggerstarted.crud.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

/**
 * @author ogbozoyan
 * @date 17.02.2023
 */
@RestControllerAdvice
public class CustomControllerAdvice {
    private static final Integer INTERNAL_SERVER_ERROR = 500; //INTERNAL_SERVER_ERROR
    private static final Integer NOT_FOUND = 404;

    private static final Integer FORBIDDEN = 403;
    private static final Integer BAD_REQUEST = 400;

    @ExceptionHandler(SaveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorMessage> saveException(SaveException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PointcutException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorMessage> pointCutException(PointcutException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeleteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorMessage> deleteException(DeleteException er, WebRequest req) {
        if (er.getMessage().contains("not found")) {
            return deleteExceptionNotFound(er, req);
        } else {
            CustomErrorMessage errorMessage = new CustomErrorMessage(
                    INTERNAL_SERVER_ERROR,
                    new Date(),
                    er.getMessage(),
                    req.getDescription(false)
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(DeleteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorMessage> deleteExceptionNotFound(DeleteException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                NOT_FOUND,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorMessage> pageException(PageException er, WebRequest req) {
        if (er.getMessage().equals("No entities found")) {
            return pageExceptionNotFound(er, req);
        } else {
            CustomErrorMessage errorMessage = new CustomErrorMessage(
                    INTERNAL_SERVER_ERROR,
                    new Date(),
                    er.getMessage(),
                    req.getDescription(false)
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(PageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorMessage> pageExceptionNotFound(PageException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                NOT_FOUND,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FilterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorMessage> filterException(FilterException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FindException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorMessage> findException(FindException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                NOT_FOUND,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UpdateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorMessage> updateException(UpdateException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorMessage> badJsonException(IllegalStateException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                BAD_REQUEST,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorMessage> entityNotFoundException(EntityNotFoundException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                NOT_FOUND,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuditException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorMessage> authenticationException(AuditException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                NOT_FOUND,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorMessage> runtimeException(RuntimeException er, WebRequest req) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}