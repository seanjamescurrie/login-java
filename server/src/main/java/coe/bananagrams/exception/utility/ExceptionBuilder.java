package coe.bananagrams.exception.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ExceptionBuilder {

    public static ResponseEntity<ErrorResponse> buildErrorResponseRepresentation(final HttpStatus httpStatus, final String message) {
        return ResponseEntity.status(httpStatus.value())
                .body(ErrorResponse.builder()
                        .path("Not specified")
                        .error(httpStatus.getReasonPhrase())
                        .message(message)
                        .timestamp(LocalDateTime.now()).build());
    }
}
