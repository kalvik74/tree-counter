package com.holidu.interview.assignment.rest.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError(HttpStatus status, LocalDateTime timestamp, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public static final class ApiErrorBuilder {
        private HttpStatus status;
        private LocalDateTime timestamp;
        private String message;

        private ApiErrorBuilder() {
        }

        public static ApiErrorBuilder anApiError() {
            return new ApiErrorBuilder();
        }

        public ApiErrorBuilder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiErrorBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiErrorBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ApiError build() {
            return new ApiError(status, timestamp, message);
        }
    }
}