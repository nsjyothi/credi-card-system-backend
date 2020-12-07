package com.publicissapient.creditcards.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCreditCardNumberException.class)
    public final ResponseEntity<Object> onInvalidCreditCardNumberException(InvalidCreditCardNumberException e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Invalid credit card number", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CreditCardNumberExists.class)
    public final ResponseEntity<Object> onCreditCardNumberExists(CreditCardNumberExists e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Duplicate Card Number", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public final ResponseEntity<Object> onConstraintViolationException(TransactionSystemException e) {
        Throwable rootCause = ((TransactionSystemException) e).getRootCause();
        if (rootCause instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) rootCause;
            List<String> errors = new ArrayList<>();
            for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
                errors.add((violation.getPropertyPath() + ": " + violation.getMessage()));
            }
            ErrorResponse errorResponse = new ErrorResponse("Invalid input fields", errors);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        ErrorResponse errorResponse = new ErrorResponse("Internal server error", null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse("Invalid or missing field values", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Content type should be application/json only", errors);
        return new ResponseEntity(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
