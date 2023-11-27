package A1B1O3.bodyrecord.exercise.domain;

import A1B1O3.bodyrecord.common.BaseEntity;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseUpdateRequest;
import A1B1O3.bodyrecord.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Time;

import static A1B1O3.bodyrecord.common.type.StatusType.USEABLE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE exercise SET status = 'DELETED' WHERE exercise_code = ?")
@Where(clause = "status = 'USEABLE'")
public class Exercise extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "exercise_code")
    private int exerciseCode;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_code")
    private Member member;

    @Column(name = "exercise_name")
    private String exerciseName;
    @Column(name = "exercise_weight")
    private float exerciseWeight;
    @Column(name = "exercise_count")
    private int exerciseCount;
    @Column(name = "exercise_time")
    @JsonFormat(pattern = "hh:mm:ss")
    private Time exerciseTime;
    @Column(name = "exercise_image_path")
    private String exerciseImagePath;
    @Column(name = "exercise_image_name")
    private String exerciseImageName;
    @Column(name = "exercise_share")
    private Boolean exerciseShare;
    @Column(name = "exercise_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date exerciseDate;

    public Exercise(
            final int exerciseCode,
            final Member member,
            final String exerciseName,
            final float exerciseWeight,
            final int exerciseCount,
            final Time exerciseTime,
            final String exerciseImagePath,
            final String exerciseImageName,
            final Boolean exerciseShare,
            final Date exerciseDate
    ) {
        super(USEABLE);
        this.exerciseCode = exerciseCode;
        this.member = member;
        this.exerciseName = exerciseName;
        this.exerciseWeight = exerciseWeight;
        this.exerciseCount = exerciseCount;
        this.exerciseTime = exerciseTime;
        this.exerciseImagePath = exerciseImagePath;
        this.exerciseImageName = exerciseImageName;
        this.exerciseShare = exerciseShare;
        this.exerciseDate = exerciseDate;
    }


    public static Exercise of(final Member member, final String exerciseName, final int exerciseCount, final float exerciseWeight, final Time exerciseTime, final Boolean exerciseShare, final String exerciseImagePath, final String exerciseImageName, final Date exerciseDate) {
        return new Exercise(
                0,
                member,
                exerciseName,
                exerciseWeight,
                exerciseCount,
                exerciseTime,
                exerciseImagePath,
                exerciseImageName,
                exerciseShare,
                exerciseDate
        );
    }

    public void update(ExerciseUpdateRequest exerciseUpdateRequest){
        this.exerciseName = exerciseUpdateRequest.getExerciseName();
        this.exerciseWeight = exerciseUpdateRequest.getExerciseWeight();
        this.exerciseCount = exerciseUpdateRequest.getExerciseCount();
        this.exerciseTime = exerciseUpdateRequest.getExerciseTime();
        this.exerciseImagePath = exerciseUpdateRequest.getExerciseImagePath();
        this.exerciseImageName = exerciseUpdateRequest.getExerciseImageName();
        this.exerciseShare = exerciseUpdateRequest.getExerciseShare();
        this.exerciseDate = exerciseUpdateRequest.getExerciseDate();
    }
}
