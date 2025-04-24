package it.at.backend.utils;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private final Integer status;

    public CustomException(Integer status, String message) {
        super(message);
        this.status=status;
    }
}
