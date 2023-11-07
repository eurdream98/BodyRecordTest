package com.example.structure.exercise.dto.response;//package com.example.structure.exercise.dto.response;
//
//import com.example.structure.exercise.domain.ExerciseLog;
//import com.example.structure.member.domain.Member;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import javax.persistence.*;
//import java.sql.Time;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//import static javax.persistence.FetchType.LAZY;
//import static javax.persistence.GenerationType.IDENTITY;
//import static lombok.AccessLevel.PRIVATE;
//
//@Getter
//@RequiredArgsConstructor(access = PRIVATE)
//public class ExerciseLogDetailResponse {
//
////    private final Long id;
////    private final String title;
////    private final String content;
////    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
////    private final LocalDateTime createdAt;
////    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
////    private final LocalDateTime modifiedAt;
////
////    public static ExerciseLogDetailResponse from(final ExerciseLog exerciseLog) {
////        return new ExerciseLogDetailResponse(
////                exerciseLog.getId(),
////                exerciseLog.getTitle(),
////                exerciseLog.getContent(),
////                exerciseLog.getCreatedAt(),
////                exerciseLog.getModifiedAt()
////        );
////    }
//
//private final Long exerciseCode;
//    private  final String exerciseName;
//
//    private Float exerciseWeight;
//
//    private int exerciseCount;
//
//    private Time exerciseTime;
//
//    private String exerciseImage;
//
//    private String exerciseShare;
//
//    private Date exerciseDate;
//
//    private Member memberCode;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private final LocalDateTime createdAt;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private final LocalDateTime modifiedAt;
//
//    public ExerciseLogDetailResponse(final ExerciseLog exerciseLog) {
//        return new ExerciseLogDetailResponse(
//                exerciseLog.getExerciseCode(),
//                exerciseLog.getExerciseName(),
//                exerciseLog.getExerciseWeight(),
//                exerciseLog.getExerciseCount(),
//                exerciseLog.getExerciseTime(),
//                exerciseLog.getExerciseImage(),
//                exerciseLog.getExerciseShare(),
//                exerciseLog.getExerciseDate(),
//                exerciseLog.getMemberCode(),
//                exerciseLog.getCreatedAt(),
//                exerciseLog.getModifiedAt()
//
//        );
//
//    }
//}
