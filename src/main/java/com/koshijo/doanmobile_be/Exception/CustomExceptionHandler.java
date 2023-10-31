package com.koshijo.doanmobile_be.Exception;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<BaseResponse> handleUserRegistrationException(UserRegistrationException ex) {
        return ResponseEntity.badRequest().body(new BaseResponse(HttpStatus.BAD_REQUEST.value(),null,ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>("Lỗi xảy ra: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
