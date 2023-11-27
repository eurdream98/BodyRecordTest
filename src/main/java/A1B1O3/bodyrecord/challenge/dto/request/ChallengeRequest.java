package A1B1O3.bodyrecord.challenge.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class ChallengeRequest {

    @NotNull(message = "챌린지 카테고리를 입력해주세요")
    private final int challengecategoryCode;

    @NotNull
    private final int memberCode;

    @NotBlank(message = "챌린지 제목을 입력해주세요")
    private final String challengeTitle;

    @NotBlank(message = "챌린지 내용을 입력해주세요")
    private final String challengeContent;

    @NotNull
    private final LocalDate challengeStartdate;

    @NotNull
    private final LocalDate challengeEnddate;


//    public ChallengeCategory getChallengeCategoryCode() {
//        return challengecategoryCode;
//    }
//
//    public int getChallengeCategoryCode() {
//        return challengecategoryCode;
//    }

}
