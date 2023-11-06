package com.example.structure.member.dto.response;

import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.MemberState;
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
    private final String memberNickname;
    private final String goalcategoryName;
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
                member.getMemberNickname(),
                member.getGoalcategoryName(),
                member.getState(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }
}
