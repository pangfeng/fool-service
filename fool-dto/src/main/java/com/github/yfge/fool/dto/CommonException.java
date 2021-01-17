package com.github.yfge.fool.dto;


import lombok.Getter;

public class CommonException extends RuntimeException {
    @Getter
    private int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }
}
