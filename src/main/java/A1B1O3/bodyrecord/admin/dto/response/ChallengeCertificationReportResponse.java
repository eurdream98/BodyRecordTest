package A1B1O3.bodyrecord.admin.dto.response;

import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.report.domain.repository.Report;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PUBLIC;

@Getter
@Setter
@RequiredArgsConstructor(access = PUBLIC)
public class ChallengeCertificationReportResponse {

    private final int reportCode;
    private final String memberName;

    public static ChallengeCertificationReportResponse from(final Report report, Member member) {
        return new ChallengeCertificationReportResponse(
                report.getReportCode(),
                member.getMemberName()
        );
    }
}
