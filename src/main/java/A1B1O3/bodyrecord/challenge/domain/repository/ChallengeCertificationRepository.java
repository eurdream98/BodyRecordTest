package A1B1O3.bodyrecord.challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeCertificationRepository extends JpaRepository<ChallengeCertification, Integer> {
//    List<ChallengeCertification> findByChallengeCode(Challenge challenge);
    List<ChallengeCertification> findByChallengeCode(Challenge challenge);
    List<ChallengeCertification> findByChallengeCodeAndMemberCode(Challenge challenge, int memberCode);
    Optional<ChallengeCertification> findByChallengecerCode(int challengecerCode);
}
