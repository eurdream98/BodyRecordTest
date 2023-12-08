package A1B1O3.bodyrecord.body.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class OnlyBodyUpdateRequest {
    @NotNull(message="몸무게를 입력해주세요")
    private final float weight;
    @NotNull(message="골격근량을 입력해주세요")
    private final float muscle;
    @NotNull(message="체지방률 입력해주세요")
    private final float fat;
}
