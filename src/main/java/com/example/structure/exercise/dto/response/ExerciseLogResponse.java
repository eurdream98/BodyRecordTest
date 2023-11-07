package com.example.structure.exercise.dto.response;//package com.example.structure.exercise.dto.response;
//
//import com.example.structure.exercise.domain.ExerciseLog;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import static lombok.AccessLevel.PRIVATE;
//
//@Getter
//@RequiredArgsConstructor(access = PRIVATE)
//public class ExerciseLogResponse {
//
//    private final Long id;
//    private final String title;
//    private final String content;
//
//    public static ExerciseLogResponse from(final ExerciseLog exerciseLog) {
//        return new ExerciseLogResponse(
//                exerciseLog.getId(),
//                exerciseLog.getTitle(),
//                exerciseLog.getContent()
//        );
//    }
//
//}
