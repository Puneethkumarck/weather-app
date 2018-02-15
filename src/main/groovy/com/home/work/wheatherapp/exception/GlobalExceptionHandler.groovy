package com.home.work.wheatherapp.exception

import com.home.work.wheatherapp.domain.ErrorDetail
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorDetail handleException(Exception ex) {

        return new ErrorDetail('500', 'internal server error');
    }
}
