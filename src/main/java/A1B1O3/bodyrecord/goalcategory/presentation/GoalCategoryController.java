package A1B1O3.bodyrecord.goalcategory.presentation;

import A1B1O3.bodyrecord.exercise.dto.response.ExerciseResponse;
import A1B1O3.bodyrecord.goalcategory.domain.GoalCategory;
import A1B1O3.bodyrecord.goalcategory.dto.response.GoalCategoryResponse;
import A1B1O3.bodyrecord.goalcategory.service.GoalCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goal/category")
public class GoalCategoryController {
    private final GoalCategoryService goalCategoryService;

//    @GetMapping
//    public ResponseEntity<List<ExerciseResponse>> getExercises(){
//        final List<ExerciseResponse> exerciseResponse = exerciseService.getAllExercise(1);
//        return ResponseEntity.ok(exerciseResponse);
//    }

    /*목표 카테고리 전체 조회*/
    @GetMapping
    public ResponseEntity<List<GoalCategoryResponse>> getGoalCategories(){
        final List<GoalCategoryResponse> goalCategoryResponse = goalCategoryService.getAllCategories();
        return ResponseEntity.ok(goalCategoryResponse);

    }

    /*목표 카테고리 등록*/

    /*목표 카테고리 수정*/

    /*목표 카테고리 삭제*/
}
