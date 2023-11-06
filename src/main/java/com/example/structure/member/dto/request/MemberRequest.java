package com.example.structure.member.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class MemberRequest {
    @NotNull(message="닉네임을 입력해주세요")
    private final String memberNickname;
    @NotNull(message="목표를 입력해주세요")
    private final String goalcategoryName;
}
