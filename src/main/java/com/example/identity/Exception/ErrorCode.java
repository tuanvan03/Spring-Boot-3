package com.example.identity.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXITS(1001, "user existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXITS(1002, "user doesn't exist", HttpStatus.NOT_FOUND),
    NOT_AUTHENTICATED(1003, "not authenticated", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(104, "you do not have permission", HttpStatus.FORBIDDEN)
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
