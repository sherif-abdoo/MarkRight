package com.MarkRight.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JSendResponseBuilder {

    public static ResponseEntity<JSendResponse> build(JSendResponse response) {
        HttpStatus status = switch (response.getStatus()) {
            case "success" -> HttpStatus.OK;
            case "fail" -> HttpStatus.BAD_REQUEST;
            case "error" -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return ResponseEntity.status(status).body(response);
    }
}
