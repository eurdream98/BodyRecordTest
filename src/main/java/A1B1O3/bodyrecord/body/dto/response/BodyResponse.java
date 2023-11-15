package A1B1O3.bodyrecord.body.dto.response;

import A1B1O3.bodyrecord.body.domain.Body;
import com.example.structure.common.type.StatusType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class BodyResponse {
    private final int bodyCode;
    private final float weight;
    private final float fat;
    private final float muscle;
    private final StatusType status;
    public static BodyResponse from(final Body body) {
        return new BodyResponse(
                body.getBodyCode(),
                body.getWeight(),
                body.getFat(),
                body.getMuscle(),
                body.getStatus()
        );
    }
}
