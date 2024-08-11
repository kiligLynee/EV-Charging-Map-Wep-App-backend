package com.charging.common;

import java.io.Serializable;

public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;           // 状态码
    private String message;     // 消息
    private Object data;        // 数据
    private long timestamp;     // 时间戳

    // 构造函数
    public ApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(int code, String message, Object data) {
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法用于快速创建成功响应
    public static ApiResponse success(Object data) {
        return new ApiResponse(200, "Success", data);
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(200, message, data);
    }

    // 静态方法用于快速创建错误响应
    public static ApiResponse error(int code, String message) {
        return new ApiResponse(code, message, null);
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}