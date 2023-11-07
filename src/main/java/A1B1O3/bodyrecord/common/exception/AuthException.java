package A1B1O3.bodyrecord.common.exception;

import A1B1O3.bodyrecord.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final int code;
    private final String message;

    public AuthException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
