package A1B1O3.bodyrecord.challenge.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PUBLIC;

@Getter
@Setter
@RequiredArgsConstructor(access = PUBLIC)
public class ChallengePopularResponse {

    private int challengeCode;
    private String challengeTitle;
    private int challengeParticipatesCount;

    public static ChallengePopularResponse fromEntity(Challenge challenge) {
        ChallengePopularResponse response = new ChallengePopularResponse();
        response.setChallengeCode(challenge.getChallengeCode());
        response.setChallengeTitle(challenge.getChallengeTitle());
        response.setChallengeParticipatesCount(challenge.getChallengeParticipates().size());
        return response;
    }
}
