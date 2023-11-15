package A1B1O3.bodyrecord.exercise.domain.repository;

import A1B1O3.bodyrecord.exercise.domain.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    List<Exercise> findAllByMemberMemberCode(final int memberCode);

    Optional<Exercise> findByExerciseCode(final int exerciseCode);

    boolean existsByMemberMemberCodeAndExerciseCode(final int memberCode, final int exerciseCode);

    Slice<Exercise> findByExerciseShareAndMemberGoalCategoryGoalCategoryCode(final boolean exerciseShare, final int goalCategoryCode, Pageable pageable);

    Slice<Exercise> findByExerciseShareAndMemberBodyWeightAndMemberBodyFatAndMemberBodyMuscle(final boolean exerciseShare, final float weight, final float fat, final float muscle, Pageable pageable);


    Slice<Exercise> findByExerciseShareAndMemberGoalCategoryGoalCategoryCodeAndMemberBodyWeightAndMemberBodyFatAndMemberBodyMuscle(final boolean exerciseShare, final int goalCategoryCode, final float weight, final float fat, final float muscle, Pageable pageable);
}