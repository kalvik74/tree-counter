package com.holidu.interview.assignment.rest.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ce) {
        logger.error("Error handler: ", ce);
        return ApiError.ApiErrorBuilder.anApiError()
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage(ce.getMessage())
                .withTimestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleIllegalArgumentException(RuntimeException ce) {
        logger.error("Error handler: ", ce);
        return ApiError.ApiErrorBuilder.anApiError()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withMessage(ce.getMessage())
                .withTimestamp(LocalDateTime.now())
                .build();
    }

}
