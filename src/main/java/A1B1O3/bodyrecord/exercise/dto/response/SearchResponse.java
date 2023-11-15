package A1B1O3.bodyrecord.exercise.dto.response;

import A1B1O3.bodyrecord.exercise.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;

@Getter
@AllArgsConstructor
public class SearchResponse {

    private final String exerciseName;

    private final float exerciseWeight;

    private final int exerciseCount;

    private final Time exerciseTime;

    private final Boolean exerciseShare;

    private final String memberNickName;


    public static SearchResponse from(final Exercise exercise){
        return new SearchResponse(
                exercise.getExerciseName(),
                exercise.getExerciseWeight(),
                exercise.getExerciseCount(),
                exercise.getExerciseTime(),
                exercise.getExerciseShare(),
                exercise.getMember().getMemberNickname()

        );
    }


}
