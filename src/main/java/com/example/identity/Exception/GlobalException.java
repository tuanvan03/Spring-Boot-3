package com.example.identity.Exception;

import com.example.identity.DTO.Request.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

// where stores all exception
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<RuntimeException>> handlingRuntimeException(RuntimeException runtimeException) {
        ApiResponse<RuntimeException> apiResponse = new ApiResponse<>();

        apiResponse.setCode(1001);
        apiResponse.setMessage(runtimeException.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingValidation(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.badRequest().body(Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<AppException>> handlingAppException(AppException appException) {
        ErrorCode errorCode = appException.getErrorCode();

        ApiResponse<AppException> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException accessDeniedException) {
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;

        return ResponseEntity.status(errorCode.getCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }
}
