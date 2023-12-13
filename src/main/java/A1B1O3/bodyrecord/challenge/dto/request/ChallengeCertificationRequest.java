package A1B1O3.bodyrecord.challenge.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
public class ChallengeCertificationRequest {

    @NotBlank
    private String challengeImage;

    @NotNull
    private final int challengeCode;

    @NotNull
    private final int memberCode;
}
