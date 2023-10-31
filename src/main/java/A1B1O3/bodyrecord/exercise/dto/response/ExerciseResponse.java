package A1B1O3.bodyrecord.exercise.dto.response;

import A1B1O3.bodyrecord.exercise.domain.Exercise;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.sql.Time;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class ExerciseResponse {


    private final Date exerciseDate;

    public static ExerciseResponse from(final Exercise exercise) {
        return new ExerciseResponse(
                exercise.getExerciseDate()
        );
    }
}