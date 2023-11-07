package com.example.structure.common.exception; /* 상황에 따라 발생 시킬 Exception 타입들을 정의 */

import com.example.structure.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final int code;
    private final String message;

    public BadRequestException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
