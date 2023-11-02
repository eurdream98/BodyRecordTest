package A1B1O3.bodyrecord.goalcategory.dto.response;

import A1B1O3.bodyrecord.goalcategory.domain.GoalCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GoalCategoryResponse {

    private final int goalCategoryCode;
    private final String goalCategoryName;
    public static GoalCategoryResponse from(final GoalCategory goalCategory) {
        return new GoalCategoryResponse(
                goalCategory.getGoalCategoryCode(),
                goalCategory.getGoalCategoryName()
        );

    }
}
