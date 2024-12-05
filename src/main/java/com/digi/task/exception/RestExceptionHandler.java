package com.digi.task.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({ EmployeeNotFoundException.class })
    public ResponseEntity<String> handleEmployeeNotFound(
            EmployeeNotFoundException ex, WebRequest request) {
        log.info("Requested Employee Is Not Found! {}", request.getDescription(true));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Requested Employee Is Not Found!");
    }

    @ExceptionHandler({ InvalidInputException.class })
    public ResponseEntity<String> handleInvalildInput(
            InvalidInputException ex, WebRequest request) {
        log.info("Requested properties are invalid! {}", String.join(", ", ex.getInvalidInputProperties()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Requested properties are invalid: " + String.join(", ", ex.getInvalidInputProperties()));
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<String> handleGeneralException(
            Exception ex, WebRequest request) {
        log.error("General Error! {} {}", request.getDescription(true), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("General Error!");
    }

    @ExceptionHandler({ ExternalServiceDownException.class })
    public ResponseEntity<String> handleExternalServiceDown(
            ExternalServiceDownException ex, WebRequest request) {
        log.error("External Service Down - will be retried again soon! {} {}", request.getDescription(true), ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK)
                .body("External Service Down - will be retried again soon!");
    }

    @ExceptionHandler({ ExternalServiceDownBlockingException.class })
    public ResponseEntity<String> handleExternalServiceDown(
            ExternalServiceDownBlockingException ex, WebRequest request) {
        log.error("External Service Down - request should fail! {} {}", request.getDescription(true), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("External Service Down - request should fail!");
    }
}
