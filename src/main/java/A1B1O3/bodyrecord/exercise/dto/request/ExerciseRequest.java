package A1B1O3.bodyrecord.exercise.dto.request;

import A1B1O3.bodyrecord.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;

@Getter
@RequiredArgsConstructor
public class ExerciseRequest {

    private final String exerciseName;

    private final float exerciseWeight;

    private final int exerciseCount;

    private final Time exerciseTime;

    private final String exerciseImage;

    private final Boolean exerciseShare;


}



