package com.self.music.common.exception;

import com.self.music.common.exception.status2xx.NoContentException;
import com.self.music.common.support.exception.ApiError;
import com.self.music.common.support.exception.ApiError.ApiSubError;
import com.self.music.common.support.exception.CustomException;
import com.self.music.common.support.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError>handleCustomException(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        HttpStatus httpStatus = errorCode.defaultHttpStatus();

        ApiError response = ApiError.of(errorCode, getPlattedApiSubErrors(exception.getCause()));

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> handlerNoContentException(NoContentException exception) {
        return ResponseEntity.noContent().build();
    }

    private ApiSubError[] getPlattedApiSubErrors(Throwable cause) {
        Throwable currentCause = cause;
        List<ApiSubError> subErrors = new ArrayList<>();

        while (currentCause != null) {

            String errorFullName = currentCause.getClass().getSimpleName();
            String field = errorFullName.substring(errorFullName.lastIndexOf('.') + 1);
            subErrors.add(
                    ApiSubError.builder()
                            .field(field)
                            .message(currentCause.getLocalizedMessage())
                            .build()
            );
            currentCause = currentCause.getCause();
        }

        ApiSubError[] subErrorArray = new ApiSubError[subErrors.size()];
        return subErrors.toArray(subErrorArray);
    }
}
