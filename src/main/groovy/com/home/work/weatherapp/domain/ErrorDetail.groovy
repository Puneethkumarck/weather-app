package com.home.work.weatherapp.domain


class ErrorDetail {

    String errorCode

    String message

    ErrorDetail(String errorCode, String message) {
        this.errorCode = errorCode
        this.message = message
    }
}
