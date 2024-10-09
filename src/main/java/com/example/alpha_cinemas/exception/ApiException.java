package com.example.alpha_cinemas.exception;


import com.example.alpha_cinemas.enums.ApiErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private final ApiErrorCode apiErrorCode;

    public ApiException(ApiErrorCode apiErrorCode){
        super(apiErrorCode.getMessage());
        this.apiErrorCode = apiErrorCode;
    }

}
