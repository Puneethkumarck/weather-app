package com.home.work.wheatherapp.domain


class ErrorDetail {

    String errorCode

    String message

    ErrorDetail(String errorCode, String message) {
        this.errorCode = errorCode
        this.message = message
    }
}
