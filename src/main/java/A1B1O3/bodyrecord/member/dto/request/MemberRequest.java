package A1B1O3.bodyrecord.member.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class MemberRequest {
    @NotNull(message="닉네임을 입력해주세요")
    private final String memberNickname;

}
