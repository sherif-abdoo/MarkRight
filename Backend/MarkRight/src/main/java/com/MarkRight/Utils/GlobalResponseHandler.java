package com.MarkRight.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalResponseHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSendResponse> handleAnyException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(JSendResponse.error(ex.getMessage(), 500));
    }
}
