package A1B1O3.bodyrecord.challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeCategoryRepository extends JpaRepository<ChallengeCategory, Integer> {
    Optional<ChallengeCategory> findByChallengecategoryCode(int challengecategoryCode);

//    ChallengeCategory findByChallengecategoryCode(int challengecategoryCode);

}
