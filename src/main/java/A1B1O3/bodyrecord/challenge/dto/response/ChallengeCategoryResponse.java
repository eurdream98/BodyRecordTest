package A1B1O3.bodyrecord.challenge.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import A1B1O3.bodyrecord.challenge.domain.repository.ChallengeCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class ChallengeCategoryResponse {

    private final int challengeCode;
    private final int challengecategoryCode; //수정 가능성
    private final String challengeTitle;
    private final LocalDate challengeStartdate; //수정 가능성
    private final LocalDate challengeEnddate; //수정 가능성
    private final String challengecategoryName; //수정 가능성

    public static ChallengeCategoryResponse from(final Challenge challenge, ChallengeCategory challengeCategory) {
        return new ChallengeCategoryResponse(

                challenge.getChallengeCode(),
                challengeCategory.getChallengecategoryCode(),
                challenge.getChallengeTitle(),
                challenge.getChallengeStartdate(),
                challenge.getChallengeEnddate(),
                challengeCategory.getChallengecategoryName()
        );
    }
}
