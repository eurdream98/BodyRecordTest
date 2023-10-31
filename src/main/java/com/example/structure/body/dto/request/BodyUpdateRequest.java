package com.example.structure.body.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class BodyUpdateRequest {
    @NotBlank(message="몸무게를 입력해주세요")
    private final float weight;
    @NotBlank(message="체지방률 입력해주세요")
    private final float fat;
    @NotBlank(message="골격근량을 입력해주세요")
    private final float muscle;
}
