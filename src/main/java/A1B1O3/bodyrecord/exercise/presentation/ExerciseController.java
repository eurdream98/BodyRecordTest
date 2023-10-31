package A1B1O3.bodyrecord.exercise.presentation;

import A1B1O3.bodyrecord.exercise.dto.request.ExerciseRequest;
import A1B1O3.bodyrecord.exercise.dto.request.ExerciseUpdateRequest;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseDetailResponse;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseResponse;
import A1B1O3.bodyrecord.exercise.dto.response.SearchResponse;
import A1B1O3.bodyrecord.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<ExerciseDetailResponse> getExercises(@PathVariable final int exerciseCode)
    {
        exerciseService.validateExerciseByMember(/* 접근자.getMemberCode() */ 1, exerciseCode);
       final ExerciseDetailResponse exerciseDetailResponse = exerciseService.getExerciseDetail(1);
       return ResponseEntity.ok(exerciseDetailResponse);
    }





}
