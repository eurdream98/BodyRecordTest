package com.example.structure.body.dto.response;

import ch.qos.logback.core.status.Status;
import com.example.structure.body.domain.Body;
import com.example.structure.common.type.StatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class BodyDetailResponse {
    private final Integer bodyCode;
    private final float weight;
    private final float fat;
    private final float muscle;
    private final StatusType status; // state 필드 추가
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public static BodyDetailResponse from(final Body body) {
        return new BodyDetailResponse(
                body.getBodyCode(),
                body.getWeight(),
                body.getFat(),
                body.getMuscle(),
                body.getStatus(), // state 값 가져오기
                body.getCreatedAt(),
                body.getModifiedAt()
        );
    }
}