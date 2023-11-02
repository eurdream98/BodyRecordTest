package A1B1O3.bodyrecord.goalcategory.service;

import A1B1O3.bodyrecord.exercise.domain.Exercise;
import A1B1O3.bodyrecord.exercise.dto.response.ExerciseResponse;
import A1B1O3.bodyrecord.goalcategory.domain.GoalCategory;
import A1B1O3.bodyrecord.goalcategory.domain.repository.GoalCategoryRepository;
import A1B1O3.bodyrecord.goalcategory.dto.response.GoalCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalCategoryService {
    private final GoalCategoryRepository goalCategoryRepository;



    @Transactional(readOnly = true)
    public List<GoalCategoryResponse> getAllCategories() {

        final List<GoalCategory> goalCategories = goalCategoryRepository.findAll();

        return goalCategories.stream()
                .map(goalCategory -> GoalCategoryResponse.from(goalCategory))
                .collect(Collectors.toList());
    }
}
