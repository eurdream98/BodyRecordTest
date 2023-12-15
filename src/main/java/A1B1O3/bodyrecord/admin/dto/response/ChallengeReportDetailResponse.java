package A1B1O3.bodyrecord.admin.dto.response;

import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.report.domain.repository.Report;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static lombok.AccessLevel.PUBLIC;

@Getter
@Setter
@RequiredArgsConstructor(access = PUBLIC)
public class ChallengeReportDetailResponse {

    private final int reportCode;
    private final String memberName;
    private final LocalDate reportDate;
    private final int challengeCode;
    private final String reportContent;

    public static ChallengeReportDetailResponse from(final Report report, Member member) {
        return new ChallengeReportDetailResponse(
                report.getReportCode(),
                member.getMemberName(),
                report.getReportDate(),
                report.getChallengeCode().getChallengeCode(),
                report.getReportContent()
        );
    }
}
