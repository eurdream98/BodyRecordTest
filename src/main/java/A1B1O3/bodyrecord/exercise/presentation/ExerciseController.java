package A1B1O3.bodyrecord.exercise.presentation;

import A1B1O3.bodyrecord.exercise.dto.request.ExerciseRequest;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseUpdateRequest;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseCalenderResponse;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseDetailResponse;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseResponse;
//import A1B1O3.bodyrecord.exercise.dto.response.SearchResponse;
import A1B1O3.bodyrecord.exercise.dto.response.SearchResponse;
import A1B1O3.bodyrecord.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;




@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise/log")
public class ExerciseController {
    private final ExerciseService exerciseService;



    /*나의 운동기록 상세 조회*/
    @GetMapping("/{exerciseCode}")
    public ResponseEntity<ExerciseDetailResponse> getExercise(@PathVariable final int exerciseCode)
    {
        exerciseService.validateExerciseByMember(/* 접근자.getMemberCode() */ 1, exerciseCode);
       final ExerciseDetailResponse exerciseDetailResponse = exerciseService.getExerciseDetail(1);
       return ResponseEntity.ok(exerciseDetailResponse);
    }

    /*운동기록 등록*/
    @PostMapping
    public ResponseEntity<Void>saveExercise(/*접근자*/ @RequestBody @Valid final ExerciseRequest exerciseRequest){
        final int exerciseCode = exerciseService.save(/*접근자.getMemberCode*/1,exerciseRequest);
        return ResponseEntity.created(URI.create("/exercise/log/"+exerciseCode)).build();
    }

    /*운동기록 수정*/
    @PutMapping("/{exerciseCode}")
    public ResponseEntity<Void> updateExercise(
            @PathVariable final int exerciseCode, @RequestBody @Valid final ExerciseUpdateRequest exerciseUpdateRequest){
        exerciseService.validateExerciseByMember(/*접근자.getMemberCode*/1, exerciseCode);
        exerciseService.update(exerciseCode, exerciseUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    /*운동기록 삭제*/
    @DeleteMapping("/{exerciseCode}")
    public ResponseEntity<Void> deleteExercise(/*접근자*/ @PathVariable final int exerciseCode){
        exerciseService.validateExerciseByMember(/*접근자.getMemberCode()*/ 1, exerciseCode);
        exerciseService.delete(exerciseCode);
        return ResponseEntity.noContent().build();
    }


    /*운동기록 체성분별 검색*/
    @GetMapping("/search/body")
    public ResponseEntity<Slice<SearchResponse>> searchBody(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                            @RequestParam(required = false, defaultValue = "0") float minWeight,
                                                            @RequestParam(required = false, defaultValue = "0") float maxWeight,
                                                            @RequestParam(required = false, defaultValue = "0") float minFat,
                                                            @RequestParam(required = false, defaultValue = "0") float maxFat,
                                                            @RequestParam(required = false, defaultValue = "0") float minMuscle,
                                                            @RequestParam(required = false, defaultValue = "0") float maxMuscle){
        final Slice<SearchResponse> searchResponse = exerciseService.searchBody(pageable, minWeight, maxWeight, minFat, maxFat, minMuscle, maxMuscle);
        return ResponseEntity.ok(searchResponse);
    }


}
