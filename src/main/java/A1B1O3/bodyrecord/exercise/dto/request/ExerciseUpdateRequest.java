package A1B1O3.bodyrecord.exercise.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;

@Getter
@RequiredArgsConstructor
public class ExerciseUpdateRequest {

    private final String exerciseName;

    private final float exerciseWeight;

    private final int exerciseCount;

    private final Time exerciseTime;

    private final String exerciseImagePath;

    private final String exerciseImageName;

    private final Boolean exerciseShare;

    private final Date exerciseDate;

    private MultipartFile imgFile;

}
