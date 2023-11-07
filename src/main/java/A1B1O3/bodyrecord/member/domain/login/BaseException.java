package A1B1O3.bodyrecord.member.domain.login;

public class BaseException extends RuntimeException{
    private final BaseResponseStatus responseStatus;

    public BaseException(BaseResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public BaseResponseStatus getResponseStatus() {
        return responseStatus;
    }
}
