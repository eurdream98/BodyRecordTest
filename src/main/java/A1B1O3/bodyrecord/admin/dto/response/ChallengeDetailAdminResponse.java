package A1B1O3.bodyrecord.admin.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static lombok.AccessLevel.PUBLIC;

@Getter
@Setter
@RequiredArgsConstructor(access = PUBLIC)
public class ChallengeDetailAdminResponse {

    private final String challengeTitle;
    private final LocalDate challengeStartdate;
    private final LocalDate challengeEnddate;
    private final String challengeContent;

    public static ChallengeDetailAdminResponse from(final Challenge challenge) {
        return new ChallengeDetailAdminResponse(
                challenge.getChallengeTitle(),
                challenge.getChallengeStartdate(),
                challenge.getChallengeEnddate(),
                challenge.getChallengeContent()
        );
    }
}
