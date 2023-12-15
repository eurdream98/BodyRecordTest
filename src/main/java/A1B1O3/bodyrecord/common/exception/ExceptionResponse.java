package A1B1O3.bodyrecord.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static lombok.AccessLevel.PRIVATE;


@Getter
@AllArgsConstructor(access = PRIVATE)
public class ExceptionResponse {
    private int status;
    private String message;


    public static ExceptionResponse of(HttpStatus status, String message) {
        return new ExceptionResponse(status.value(), message);
    }
}
