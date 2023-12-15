package A1B1O3.bodyrecord.exercise.dto.response;

import A1B1O3.bodyrecord.exercise.domain.Exercise;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ExerciseDetailResponse {
    private final String exerciseName;

    private final Float exerciseWeight;

    private final Integer exerciseCount;

    private final Time exerciseTime;

    private final String exerciseImagePath;

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
                exercise.getExerciseImagePath(),
                exercise.getExerciseShare(),
                exercise.getExerciseDate(),
                exercise.getCreatedAt(),
                exercise.getModifiedAt()
        );
    }
}
