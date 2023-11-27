package A1B1O3.bodyrecord.challenge.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import A1B1O3.bodyrecord.challenge.domain.repository.ChallengeCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class ChallengeNewResponse {

    private final int challengeCode;
    private final String challengeTitle;

    public static ChallengeNewResponse from(final Challenge challenge) {
        return new ChallengeNewResponse(
                challenge.getChallengeCode(),
                challenge.getChallengeTitle()
        );
    }
}
