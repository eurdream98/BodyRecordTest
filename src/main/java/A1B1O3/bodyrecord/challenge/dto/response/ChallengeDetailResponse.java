package A1B1O3.bodyrecord.challenge.dto.response;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class ChallengeDetailResponse {

    private final int challengeCode;
    private final String challengeTitle;
    private final String challengeContent;
    private final LocalDate challengeStartdate;
    private final LocalDate challengeEnddate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public static ChallengeDetailResponse from(final Challenge challenge) {
        return new ChallengeDetailResponse(
                challenge.getChallengeCode(),
                challenge.getChallengeTitle(),
                challenge.getChallengeContent(),
                challenge.getChallengeStartdate(),
                challenge.getChallengeEnddate(),
                challenge.getCreatedAt(),
                challenge.getModifiedAt()
        );
    }
}
