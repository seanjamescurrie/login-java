package coe.bananagrams.exception;

import coe.bananagrams.exception.custom.ResourceNotFoundException;
import coe.bananagrams.exception.utility.ErrorResponse;
import coe.bananagrams.exception.utility.ExceptionBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.NO_CONTENT, exception.getMessage());
    }
}
