package com.MarkRight.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JSendResponse {
    private String status;
    private Map<String, Object> data;
    private String message;
    private Integer code;

    public static JSendResponse success(Map<String, Object> data) {
        return new JSendResponse("success", data, null, null);
    }

    public static JSendResponse fail(Map<String, Object> data) {
        return new JSendResponse("fail", data, null, null);
    }

    public static JSendResponse error(String message, Integer code) {
        return new JSendResponse("error", null, message, code);
    }
}
