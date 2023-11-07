package com.example.structure.exercise.service;//package com.example.structure.exercise.service;
//
//import com.example.structure.common.exception.AuthException;
//import com.example.structure.common.exception.BadRequestException;
//import com.example.structure.exercise.domain.ExerciseLog;
//import com.example.structure.exercise.domain.repository.ExerciseLogRepository;
//import com.example.structure.exercise.dto.request.ExerciseLogRequest;
//import com.example.structure.exercise.dto.request.ExerciseLogUpdateRequest;
//import com.example.structure.exercise.dto.response.ExerciseLogDetailResponse;
//import com.example.structure.exercise.dto.response.ExerciseLogResponse;
//import com.example.structure.member.domain.Member;
//import com.example.structure.member.domain.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.example.structure.common.exception.type.ExceptionCode.*;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ExerciseLogService {
//
//    private final ExerciseLogRepository exerciseLogRepository;
//    private final MemberRepository memberRepository;
//
//    @Transactional(readOnly = true)
//    public void validateExcerciseLogByMember(final Long memberId, final Long exerciseLogId) {
//        if (!exerciseLogRepository.existsByMemberIdAndId(memberId, exerciseLogId)) {
//            throw new AuthException(INVALID_EXERCISE_LOG_WITH_MEMBER);
//        }
//    }
//
//    public Long save(final Long memberId, final ExerciseLogRequest exerciseLogRequest) {
//
//        final Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new BadRequestException(NOT_FOUND_MEMBER_ID));
//
//        final ExerciseLog newExercieLog = ExerciseLog.of(
//                member,
//                exerciseLogRequest.getTitle(),
//                exerciseLogRequest.getContent()
//        );
//
//        final ExerciseLog exerciseLog = exerciseLogRepository.save(newExercieLog);
//
//        return exerciseLog.getId();
//    }
//    @Transactional(readOnly = true)
//    public List<ExerciseLogResponse> getAllExerciseLogs(final Long memberId) {
//
//        final List<ExerciseLog> exerciseLogs = exerciseLogRepository.findAllByMemberId(memberId);
//
//        return exerciseLogs.stream()
//                .map(exerciseLog -> ExerciseLogResponse.from(exerciseLog))
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    public ExerciseLogDetailResponse getExerciseLogDetail(final Long exerciseLogId) {
//
//        final ExerciseLog exerciseLog = exerciseLogRepository.findById(exerciseLogId)
//                .orElseThrow(() -> new BadRequestException(NOT_FOUND_EXERCISE_LOG_ID));
//
//        return ExerciseLogDetailResponse.from(exerciseLog);
//    }
//
//    public void update(final Long exerciseLogId, final ExerciseLogUpdateRequest exerciseLogUpdateRequest) {
//
//        final ExerciseLog exerciseLog = exerciseLogRepository.findById(exerciseLogId)
//                .orElseThrow(() -> new BadRequestException(NOT_FOUND_EXERCISE_LOG_ID));
//
//        exerciseLog.update(exerciseLogUpdateRequest);
//        exerciseLogRepository.save(exerciseLog);
//    }
//
//    public void delete(final Long exerciseLogId) {
//
//        final ExerciseLog exerciseLog = exerciseLogRepository.findById(exerciseLogId)
//                .orElseThrow(() -> new BadRequestException(NOT_FOUND_EXERCISE_LOG_ID));
//
//        exerciseLogRepository.delete(exerciseLog);
//    }
//}
