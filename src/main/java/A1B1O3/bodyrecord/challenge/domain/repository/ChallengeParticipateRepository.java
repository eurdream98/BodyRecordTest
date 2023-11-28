package A1B1O3.bodyrecord.challenge.domain.repository;


import A1B1O3.bodyrecord.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ChallengeParticipateRepository extends JpaRepository<ChallengeParticipate, Integer> {

    /* 3. 참여중인 챌린지 조회 */
    List<ChallengeParticipate> findByMemberCode(int memberCode);

    /* 챌린지 탈퇴 */
    List<ChallengeParticipate> findByChallengeCodeAndMemberCodeAndState(
            Challenge challengeCode, Member memberCode, ChallengeParticipateState state);

//    ChallengeParticipate findByChallengeparticipateCode(int challengeParticipateCode);

    /* 참여중인 챌린지 조회 */
    List<ChallengeParticipate> findByMemberCodeAndState(Member memberCode, ChallengeParticipateState state);

    /* 달성률 */
    Optional<ChallengeParticipate> findFirstByChallengeCodeAndMemberCodeAndStateOrderByCreatedAtAsc(
            Challenge challengeCode, Member memberCode, ChallengeParticipateState state);
//    Optional<ChallengeParticipate> findFirstByChallengeCodeAndStateOrderByCreatedAtAsc(int challengeCode, ChallengeParticipateState state);

    /* 챌린지 삭제할때 참여도 삭제 */
    @Transactional
    @Modifying
    @Query("UPDATE ChallengeParticipate cp SET cp.state = 'LEAVE' WHERE cp.challengeCode = :challenge AND cp.state = 'JOIN'")
    void leaveByChallengeCode(@Param("challenge") Challenge challenge);

    /* 현재 회원 수 */
    List<ChallengeParticipate> findByChallengeCodeAndState(Challenge challenge, ChallengeParticipateState state);


}
