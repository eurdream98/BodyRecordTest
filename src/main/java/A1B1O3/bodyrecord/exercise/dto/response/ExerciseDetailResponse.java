package A1B1O3.bodyrecord.exercise.dto.response;

import A1B1O3.bodyrecord.exercise.domain.Exercise;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ExerciseDetailResponse {
    private final String exerciseName;

    private final float exerciseWeight;

    private final int exerciseCount;

    private final Time exerciseTime;

    private final String exerciseImage;

    private final Boolean exerciseShare;

    private final Date exerciseDate;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private final LocalDateTime modifiedAt;

    public static ExerciseDetailResponse from(final Exercise exercise) {
        return new ExerciseDetailResponse(
                exercise.getExerciseName(),
                exercise.getExerciseWeight(),
                exercise.getExerciseCount(),
                exercise.getExerciseTime(),
                exercise.getExerciseImage(),
                exercise.getExerciseShare(),
                exercise.getExerciseDate(),
                exercise.getCreatedAt(),
                exercise.getModifiedAt()
        );
    }
}
