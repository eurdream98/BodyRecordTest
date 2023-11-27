package A1B1O3.bodyrecord.challenge.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static lombok.AccessLevel.PUBLIC;

@Setter
@Getter
@RequiredArgsConstructor(access = PUBLIC)
public class MyChallengeResponse {

    private final String challengeTitle;
    private final LocalDate challengeStartdate;
    private final LocalDate challengeEnddate;

    public static MyChallengeResponse from(Challenge challenge) {
        return new MyChallengeResponse(
                challenge.getChallengeTitle(),
                challenge.getChallengeStartdate(),
                challenge.getChallengeEnddate()
        );
    }
}
