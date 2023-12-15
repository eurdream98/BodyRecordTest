package A1B1O3.bodyrecord.challenge.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class ChallengeDetailResponse {

    private final int challengeCode;
    private final String challengeTitle;
    private final String challengeContent;
    private final LocalDate challengeStartdate;
    private final LocalDate challengeEnddate;

    public static ChallengeDetailResponse from(final Challenge challenge) {
        return new ChallengeDetailResponse(
                challenge.getChallengeCode(),
                challenge.getChallengeTitle(),
                challenge.getChallengeContent(),
                challenge.getChallengeStartdate(),
                challenge.getChallengeEnddate()
        );
    }
}
