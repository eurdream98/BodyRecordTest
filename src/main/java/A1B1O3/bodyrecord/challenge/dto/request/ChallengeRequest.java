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

    @NotNull
    private final int challengecategoryCode;

    @NotNull
    private final int memberCode;

    @NotBlank
    private final String challengeTitle;

    @NotBlank
    private final String challengeContent;

    @NotNull
    private final LocalDate challengeStartdate;

    @NotNull
    private final LocalDate challengeEnddate;

}
