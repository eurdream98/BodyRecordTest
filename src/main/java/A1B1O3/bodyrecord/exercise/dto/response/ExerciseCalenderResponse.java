package A1B1O3.bodyrecord.exercise.dto.response;

import A1B1O3.bodyrecord.exercise.domain.Exercise;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExerciseCalenderResponse {
    private final int year;
    private final int month;
    private final int day;

    public static ExerciseCalenderResponse from(final Exercise exercise){
        return new ExerciseCalenderResponse(
                exercise.getCreatedAt().getYear(),
                exercise.getCreatedAt().getMonthValue(),
                exercise.getCreatedAt().getDayOfMonth()
        );
    }

}
