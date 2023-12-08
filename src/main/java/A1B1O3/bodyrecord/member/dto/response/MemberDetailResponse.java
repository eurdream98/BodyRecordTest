package A1B1O3.bodyrecord.member.dto.response;

import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.member.domain.MemberState;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class MemberDetailResponse {
    private final int memberCode;
    private final String memberSocialid;
    private final String memberName;
    private final String memberImage;
    private final String memberNickname;
    private final MemberState status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public static MemberDetailResponse from(final Member member) {
        return new MemberDetailResponse(
                member.getMemberCode(),
                member.getMemberSocialid(),
                member.getMemberName(),
                member.getMemberImage(),
                member.getMemberNickname(),
                member.getState(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }
}
