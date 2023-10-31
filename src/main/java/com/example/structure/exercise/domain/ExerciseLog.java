package com.example.structure.exercise.domain;//package com.example.structure.exercise.domain;
//
//import com.example.structure.common.BaseEntity;
//import com.example.structure.exercise.dto.request.ExerciseLogRequest;
//import com.example.structure.exercise.dto.request.ExerciseLogUpdateRequest;
//import com.example.structure.member.domain.Member;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.Where;
//import javax.persistence.*;
//
//import java.sql.Time;
//import java.util.Date;
//
//import static com.example.structure.common.type.StatusType.USABLE;
//import static javax.persistence.FetchType.LAZY;
//import static javax.persistence.GenerationType.IDENTITY;
//import static lombok.AccessLevel.PROTECTED;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = PROTECTED)
//@SQLDelete(sql = "UPDATE exercise_log SET status = 'DELETED' WHERE id = ?")
//@Where(clause = "status = 'USABLE'")
//public class ExerciseLog extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    private Long exerciseCode;
//
//    @Column(nullable = false, length = 100)
//    private String exerciseName;
//
//    @Column(nullable = false, length = 100)
//    private Float exerciseWeight;
//
//    @Column(nullable = false, length = 100)
//    private int exerciseCount;
//
//    @Column(nullable = false, length = 100)
//    private Time exerciseTime;
//
//    @Column(nullable = false, length = 100)
//    private String exerciseImage;
//
//    @Column(nullable = false, length = 100)
//    private String exerciseShare;
//
//    @Column(nullable = false, length = 100)
//    private Date exerciseDate;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "member_code")
//    private Member memberCode;
//
//    public ExerciseLog(Long exerciseCode, String exerciseName, Float exerciseWeight, int exerciseCount, Time exerciseTime, String exerciseImage, String exerciseShare, Date exerciseDate, Member memberCode) {
//        super(USABLE);
//        this.exerciseCode = exerciseCode;
//        this.exerciseName = exerciseName;
//        this.exerciseWeight = exerciseWeight;
//        this.exerciseCount = exerciseCount;
//        this.exerciseTime = exerciseTime;
//        this.exerciseImage = exerciseImage;
//        this.exerciseShare = exerciseShare;
//        this.exerciseDate = exerciseDate;
//        this.memberCode = memberCode;
//    }
//
//    //    public ExerciseLog(
////            final Long id,
////            final Member member,
////            final String title,
////            final String content
////    ) {
////        super(USABLE);
////        this.id = id;
////        this.member = member;
////        this.title = title;
////        this.content = content;
////    }
//
////    public static ExerciseLog of(final Member member, final String title, final String content) {
////        return new ExerciseLog(
////                null,
////                member,
////                title,
////                content
////        );
////    }
//
//
//    public static ExerciseLog of( String exerciseName, Float exerciseWeight, int exerciseCount, Time exerciseTime, String exerciseImage, String exerciseShare, Date exerciseDate,Member memberCode) {
//        return new ExerciseLog(
//                null,
//                exerciseName,
//                exerciseWeight,
//                exerciseCount,
//                exerciseTime,
//                exerciseImage,
//                exerciseShare,
//                exerciseDate,
//                memberCode
//        );
//    }
////    public void update(ExerciseLogUpdateRequest exerciseLogUpdateRequest) {
////        this.title = exerciseLogUpdateRequest.getTitle();
////        this.content = exerciseLogUpdateRequest.getContent();
////    }
//    public void update(ExerciseLogUpdateRequest exerciseLogUpdateRequest){
//        this.exerciseName=exerciseLogUpdateRequest.getExerciseName();
//        this.exerciseWeight=exerciseLogUpdateRequest.getExerciseWeight();
//        this.exerciseCount=exerciseLogUpdateRequest.getExerciseCount();
//        this.exerciseTime=exerciseLogUpdateRequest.getExerciseTime();
//        this.exerciseImage=exerciseLogUpdateRequest.getExerciseImage();
//        this.exerciseShare=exerciseLogUpdateRequest.getExerciseShare();
//        this.exerciseDate=exerciseLogUpdateRequest.getExerciseDate();
//    }
//
//}
