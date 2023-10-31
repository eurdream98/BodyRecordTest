package A1B1O3.bodyrecord.exception.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    INVALID_REQUEST(1000, "올바르지 않은 요청입니다."),
    NOT_FOUND_MEMBER_ID(1001, "요청한 ID에 해당하는 멤버가 존재하지 않습니다."),

    INVALID_EXERCISE_LOG_WITH_MEMBER(2000, "요청한 멤버와 ID에 해당하는 운동 기록이 존재하지 않습니다."),
    NOT_FOUND_EXERCISE_CODE(2001, "ID에 해당하는 운동 기록이 존재하지 않습니다");

    private final int code;
    private final String message;
}
