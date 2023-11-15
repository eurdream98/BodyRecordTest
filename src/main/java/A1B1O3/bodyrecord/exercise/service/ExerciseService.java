package A1B1O3.bodyrecord.exercise.service;
import A1B1O3.bodyrecord.body.domain.Body;
import A1B1O3.bodyrecord.body.domain.repository.BodyRepository;
import A1B1O3.bodyrecord.common.exception.AuthException;
import A1B1O3.bodyrecord.common.exception.BadRequestException;
import A1B1O3.bodyrecord.exercise.domain.Exercise;
import A1B1O3.bodyrecord.exercise.domain.repository.ExerciseRepository;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseRequest;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseUpdateRequest;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseCalenderResponse;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseDetailResponse;

//import A1B1O3.bodyrecord.exercise.dto.response.SearchResponse;
import A1B1O3.bodyrecord.exercise.dto.response.SearchResponse;
//import A1B1O3.bodyrecord.member.Member;
//import A1B1O3.bodyrecord.member.repository.MemberRepository;
import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static A1B1O3.bodyrecord.common.exception.type.ExceptionCode.*;


@Service
@RequiredArgsConstructor
@Transactional
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final MemberRepository memberRepository;
    private final BodyRepository bodyRepository;


    @Transactional(readOnly = true)
    public ExerciseDetailResponse getExerciseDetail(final int exerciseCode) {

        final Exercise exercise = exerciseRepository.findByExerciseCode(exerciseCode)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_EXERCISE_LOG_ID));
        return ExerciseDetailResponse.from(exercise);
    }

    @Transactional(readOnly = true)
    public void validateExerciseByMember(final int memberCode, final int exerciseCode) {
        if (!exerciseRepository.existsByMemberMemberCodeAndExerciseCode(memberCode, exerciseCode)) {
            throw new AuthException(INVALID_EXERCISE_LOG_WITH_MEMBER);
        }
    }

    public int save(final int memberCode, ExerciseRequest exerciseRequest) {
        final Member member = memberRepository.findByMemberCode(memberCode)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_MEMBER_ID));

        final Exercise newExercise = Exercise.of(
                member,
                exerciseRequest.getExerciseName(),
                exerciseRequest.getExerciseCount(),
                exerciseRequest.getExerciseWeight(),
                exerciseRequest.getExerciseTime(),
                exerciseRequest.getExerciseShare(),
                exerciseRequest.getExerciseImage()

        );

        final Exercise exercise = exerciseRepository.save(newExercise);

        return exercise.getExerciseCode();
    }

    public void update(int exerciseCode, ExerciseUpdateRequest exerciseUpdateRequest) {
        final  Exercise exercise = exerciseRepository.findByExerciseCode(exerciseCode)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_EXERCISE_LOG_ID));

        exercise.update(exerciseUpdateRequest);
        exerciseRepository.save(exercise);
    }

    public void delete(final int exerciseCode) {
        final  Exercise exercise = exerciseRepository.findByExerciseCode(exerciseCode)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_EXERCISE_LOG_ID));
        exerciseRepository.delete(exercise);
    }

    @Transactional(readOnly = true)
    public Slice<SearchResponse> searchBody(Pageable pageable, float minWeight, float maxWeight, float minFat, float maxFat, float minMuscle, float maxMuscle){
        List<Exercise> searchBodyExercise = exerciseRepository.findByExerciseShareIsTrue();
        if (minWeight > 0 && maxWeight > 0 && minFat > 0 && maxFat > 0 && minMuscle > 0 && maxMuscle > 0){
            List<Body> body = bodyRepository.findByWeightBetweenAndFatBetweenAndMuscleBetween(minWeight, maxWeight, minFat, maxFat, minMuscle, maxMuscle);
            List<Exercise> exercise = exerciseRepository.findByExerciseShareIsTrue();

            searchBodyExercise = exercise.stream().filter(e -> body.stream().anyMatch
                    (b -> e.getMember().equals(b.getMemberCode()))).collect(Collectors.toList());
        }
        return new SliceImpl<>(searchBodyExercise.stream().map(exercise -> SearchResponse.from(exercise)).collect(Collectors.toList()));


    }


}
