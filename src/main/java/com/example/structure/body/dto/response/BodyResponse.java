package com.example.structure.body.dto.response;

import com.example.structure.body.domain.Body;
import com.example.structure.common.type.StatusType;
//import com.example.structure.exercise.domain.ExerciseLog;
//import com.example.structure.exercise.dto.response.ExerciseLogDetailResponse;
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
