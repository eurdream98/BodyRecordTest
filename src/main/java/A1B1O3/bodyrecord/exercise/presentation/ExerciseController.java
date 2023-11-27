package A1B1O3.bodyrecord.exercise.presentation;

import A1B1O3.bodyrecord.auth.domain.PrincipalDetails;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseRequest;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseUpdateRequest;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseDetailResponse;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseResponse;
//import A1B1O3.bodyrecord.exercise.dto.response.SearchResponse;
import A1B1O3.bodyrecord.exercise.dto.response.SearchResponse;
import A1B1O3.bodyrecord.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;




@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise/log")
public class ExerciseController {
    private final ExerciseService exerciseService;

    /*나의 운동기록 전체 조회*/
    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getExercises(@AuthenticationPrincipal PrincipalDetails principalDetails){
        final List<ExerciseResponse> exerciseResponse = exerciseService.getAllExercise(principalDetails.getMember().getMemberCode());
        return ResponseEntity.ok(exerciseResponse);
    }

    /*나의 운동기록 상세 조회*/
    @GetMapping("/{exerciseCode}")
    public ResponseEntity<ExerciseDetailResponse> getExercise(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable final int exerciseCode)
    {
        exerciseService.validateExerciseByMember(principalDetails.getMember().getMemberCode(), exerciseCode);
       final ExerciseDetailResponse exerciseDetailResponse = exerciseService.getExerciseDetail(exerciseCode);
       return ResponseEntity.ok(exerciseDetailResponse);
    }

    /*운동기록 등록*/
    @PostMapping
    public ResponseEntity<Void>saveExercise(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute @Valid final ExerciseRequest exerciseRequest) throws IOException {
        final int exerciseCode = exerciseService.save(principalDetails.getMember().getMemberCode(),exerciseRequest);

        return ResponseEntity.created(URI.create("/exercise/log/"+exerciseCode)).build();
    }

    /*운동기록 수정*/
    @PutMapping("/{exerciseCode}")
    public ResponseEntity<Void> updateExercise(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                               @PathVariable final int exerciseCode,
                                               @ModelAttribute @Valid final ExerciseUpdateRequest exerciseUpdateRequest) throws IOException {
        exerciseService.validateExerciseByMember(principalDetails.getMember().getMemberCode(), exerciseCode);
        exerciseService.update(exerciseCode, exerciseUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    /*운동기록 삭제*/
    @DeleteMapping("/{exerciseCode}")
    public ResponseEntity<Void> deleteExercise(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable final int exerciseCode){
        exerciseService.validateExerciseByMember(principalDetails.getMember().getMemberCode(), exerciseCode);
        exerciseService.delete(exerciseCode);
        return ResponseEntity.noContent().build();
    }


    /*운동기록 체성분별 검색*/
    @GetMapping("/search/body")
    public ResponseEntity<Slice<SearchResponse>> searchBody(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                            @RequestParam(required = false, defaultValue = "0.0") float minWeight,
                                                            @RequestParam(required = false, defaultValue = "0.0") float maxWeight,
                                                            @RequestParam(required = false, defaultValue = "0.0") float minFat,
                                                            @RequestParam(required = false, defaultValue = "0.0") float maxFat,
                                                            @RequestParam(required = false, defaultValue = "0.0") float minMuscle,
                                                            @RequestParam(required = false, defaultValue = "0.0") float maxMuscle){
        final Slice<SearchResponse> searchResponse = exerciseService.searchBody(pageable, minWeight, maxWeight, minFat, maxFat, minMuscle, maxMuscle);
        return ResponseEntity.ok(searchResponse);
    }


}
