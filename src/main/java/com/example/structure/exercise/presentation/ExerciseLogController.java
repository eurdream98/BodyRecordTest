package com.example.structure.exercise.presentation;//package com.example.structure.exercise.presentation;
//
//import com.example.structure.exercise.dto.request.ExerciseLogRequest;
//import com.example.structure.exercise.dto.request.ExerciseLogUpdateRequest;
//import com.example.structure.exercise.dto.response.ExerciseLogDetailResponse;
//import com.example.structure.exercise.dto.response.ExerciseLogResponse;
//import com.example.structure.exercise.service.ExerciseLogService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/exercise/log")
//public class ExerciseLogController {
//
//    private final ExerciseLogService exerciseLogService;
//
//    /* 1. 운동 기록 저장 */
//    @PostMapping
//    /* 사전 인증 인가 확인 */
//    public ResponseEntity<Void> saveExerciseLog(
//            /* 접근자(로그인 유저) */
//            /* 유효성 검사 된 request parameter */
//            @RequestBody @Valid final ExerciseLogRequest exerciseLogRequest
//    ) {
//        final Long exerciseLogId = exerciseLogService.save(/* 접근자.getMemberId() */ 1L, exerciseLogRequest);
//        return ResponseEntity.created(URI.create("/exercise/log/" + exerciseLogId)).build();
//    }
//
//    /* 2. 로그인 한 유저의 운동 기록 모두 조회 */
//    @GetMapping
//    /* 사전 인증 인가 확인 */
//    public ResponseEntity<List<ExerciseLogResponse>> getExerciseLogs(
//            /* 접근자 */
//    ) {
//        final List<ExerciseLogResponse> exerciseLogResponse = exerciseLogService.getAllExerciseLogs(/* 접근자.getMemberId() */ 1L);
//        return ResponseEntity.ok(exerciseLogResponse);
//    }
//
//    /* 3. 로그인 한 유저의 특정 운동 기록 조회 */
//    @GetMapping("/{exerciseLogId}")
//    /* 사전 인증 인가 확인 */
//    public ResponseEntity<ExerciseLogDetailResponse> getExerciseLog(
//            /* 접근자 */
//            @PathVariable final Long exerciseLogId
//    ) {
//        /* 로그인 유저가 작성한 운동 기록이 맞는지 확인 */
//        exerciseLogService.validateExcerciseLogByMember(/* 접근자.getMemberId() */ 1L, exerciseLogId);
//        final ExerciseLogDetailResponse exerciseLogDetailResponse = exerciseLogService.getExerciseLogDetail(exerciseLogId);
//        return ResponseEntity.ok(exerciseLogDetailResponse);
//    }
//
//    /* 4. 운동 기록 수정 */
//    @PutMapping("/{exerciseLogId}")
//    /* 사전 인증 인가 확인 */
//    public ResponseEntity<Void> updateExerciseLog(
//            /* 접근자 */
//            @PathVariable final Long exerciseLogId,
//            /* 유효성 검사 된 request parameter */
//            @RequestBody @Valid final ExerciseLogUpdateRequest exerciseLogUpdateRequest
//    ) {
//        /* 로그인 유저가 작성한 운동 기록이 맞는지 확인 */
//        exerciseLogService.validateExcerciseLogByMember(/* 접근자.getMemberId() */ 1L, exerciseLogId);
//        exerciseLogService.update(exerciseLogId, exerciseLogUpdateRequest);
//        return ResponseEntity.noContent().build();
//    }
//
//    /* 5. 운동 기록 삭제 */
//    @DeleteMapping("/{exerciseLogId}")
//    /* 사전 인증 인가 확인 */
//    public ResponseEntity<Void> updateExerciseLog(
//            /* 접근자 */
//            @PathVariable final Long exerciseLogId
//    ) {
//        /* 로그인 유저가 작성한 운동 기록이 맞는지 확인 */
//        exerciseLogService.validateExcerciseLogByMember(/* 접근자.getMemberId() */ 1L, exerciseLogId);
//        exerciseLogService.delete(exerciseLogId);
//        return ResponseEntity.noContent().build();
//    }
//
//}
