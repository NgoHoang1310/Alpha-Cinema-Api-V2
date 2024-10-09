package com.example.alpha_cinemas.exception;

import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.enums.ApiErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleGlobalException(Exception exception) {
        System.out.println(exception.getMessage());
        return ApiResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(exception.getMessage()).build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException exception) {
        String messageDefault = Optional.ofNullable(exception.getFieldError()).map(FieldError::getDefaultMessage).orElse("Unknown error");
        return ApiResponse.builder().code(HttpStatus.BAD_REQUEST.value()).message(messageDefault).build();
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse<?>> handleApiException(ApiException exception) {
        ApiErrorCode apiErrorCode = exception.getApiErrorCode();
        HttpStatus status = HttpStatus.valueOf(apiErrorCode.getCode());

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(apiErrorCode.getCode())
                .message(apiErrorCode.getMessage())
                .build();

        return ResponseEntity.status(status).body(apiResponse);

    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<?> handleAuthenticationException(Exception exception) {
        return ApiResponse.builder().code(HttpStatus.UNAUTHORIZED.value()).message(exception.getMessage()).build();
    }

    @ExceptionHandler (value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<?> handleAccessDeniedException(Exception exception){
        return ApiResponse.builder().code(HttpStatus.FORBIDDEN.value()).message(exception.getMessage()).build();
    }
}
