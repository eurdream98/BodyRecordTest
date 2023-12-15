package A1B1O3.bodyrecord.exercise.domain;

import A1B1O3.bodyrecord.common.BaseEntity;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseUpdateRequest;
import A1B1O3.bodyrecord.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

import static A1B1O3.bodyrecord.common.type.StatusType.USEABLE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE exercise SET state = 'DELETED' WHERE exercise_code = ?")
@Where(clause = "state = 'USEABLE'")
@DynamicUpdate
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
    private Float exerciseWeight;
    @Column(name = "exercise_count")
    private Integer exerciseCount;
    @Column(name = "exercise_time")
    private Time exerciseTime;
    @Column(name = "exercise_image_path")
    private String exerciseImagePath;
    @Column(name = "exercise_share")
    private Boolean exerciseShare;
    @Column(name = "exercise_date")
    private Date exerciseDate;

    public Exercise(
            final int exerciseCode,
            final Member member,
            final String exerciseName,
            final Float exerciseWeight,
            final Integer exerciseCount,
            final Time exerciseTime,
            final String exerciseImagePath,
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
        this.exerciseShare = exerciseShare;
        this.exerciseDate = exerciseDate;
    }


    public static Exercise of(final Member member, final String exerciseName, final Integer exerciseCount, final Float exerciseWeight, final Time exerciseTime, final Boolean exerciseShare, final String exerciseImagePath, final Date exerciseDate) {
        return new Exercise(
                0,
                member,
                exerciseName,
                exerciseWeight,
                exerciseCount,
                exerciseTime,
                exerciseImagePath,
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
        this.exerciseShare = exerciseUpdateRequest.getExerciseShare();
        this.exerciseDate = exerciseUpdateRequest.getExerciseDate();
    }
}
