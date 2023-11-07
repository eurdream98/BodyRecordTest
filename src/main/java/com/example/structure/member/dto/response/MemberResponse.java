package com.example.structure.member.dto.response;

import com.example.structure.body.domain.Body;
import com.example.structure.body.dto.response.BodyResponse;
import com.example.structure.common.type.StatusType;
import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.MemberState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//import java.lang.reflect.Member;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class MemberResponse {
    private final int memberCode;
    private final String memberSocialid;
    private final String memberName;
    private final String memberNickname;
    private final String goalcategoryName;
    private final MemberState status;
    public static MemberResponse from(final Member member) {
        return new MemberResponse(
                member.getMemberCode(),
                member.getMemberSocialid(),
                member.getMemberName(),
                member.getMemberNickname(),
                member.getGoalcategoryName(),
                member.getState()
        );
    }


}
