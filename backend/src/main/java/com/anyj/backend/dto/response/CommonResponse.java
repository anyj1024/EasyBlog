package com.anyj.backend.dto.response;

import lombok.Data;

@Data
public class CommonResponse {
    private int code;
    private String message;
    private Object data;

    public CommonResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResponse success() {
        return new CommonResponse(200, "请求成功", null);
    }

    public static CommonResponse success(Object data) {
        return new CommonResponse(200, "请求成功", data);
    }

    public static CommonResponse success(String message, Object data) {
        return new CommonResponse(200, message, data);
    }

    public static CommonResponse fail(int code, String message) {
        return new CommonResponse(code, message, null);
    }

    public static CommonResponse fail(int code, String message, Object data) {
        return new CommonResponse(code, message, data);
    }


}
