package A1B1O3.bodyrecord.admin.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PUBLIC;

@Setter
@Getter
@RequiredArgsConstructor(access = PUBLIC)
public class ChallengeAdminResponse {

    private final int challengeCode;
    private final String challengeTitle;

    public static ChallengeAdminResponse from(final Challenge challenge) {
        return new ChallengeAdminResponse(
                challenge.getChallengeCode(),
                challenge.getChallengeTitle()
        );
    }
}
