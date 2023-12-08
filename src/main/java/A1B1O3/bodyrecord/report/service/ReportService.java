package A1B1O3.bodyrecord.report.service;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import A1B1O3.bodyrecord.challenge.domain.repository.ChallengeCertification;
import A1B1O3.bodyrecord.challenge.domain.repository.ChallengeCertificationRepository;
import A1B1O3.bodyrecord.challenge.domain.repository.ChallengeRepository;
import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.member.domain.repository.MemberRepository;
import A1B1O3.bodyrecord.report.domain.repository.Report;
import A1B1O3.bodyrecord.report.domain.repository.ReportCategory;
import A1B1O3.bodyrecord.report.domain.repository.ReportCategoryRepository;
import A1B1O3.bodyrecord.report.domain.repository.ReportRepository;
import A1B1O3.bodyrecord.report.dto.request.ReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static A1B1O3.bodyrecord.report.domain.repository.ReportState.UNPROCESSED;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;
    private final ChallengeCertificationRepository challengeCertificationRepository;
    private final ReportRepository reportRepository;
    private final ReportCategoryRepository reportCategoryRepository;

    /* 1. 챌린지 신고 */
    public void reportChallenge(int memberCode, int challengeCode, ReportRequest reportRequest) {
        Challenge challenge = challengeRepository.findByChallengeCode(challengeCode)
                .orElseThrow(() -> new EntityNotFoundException("챌린지를 찾을 수 없습니다."));

        Member member = (Member) memberRepository.findByMemberCode(memberCode)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        ReportCategory reportCategory = reportCategoryRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("신고 카테고리를 찾을 수 없습니다."));

        Report report = new Report();
        report.setReportDate(LocalDate.now());
        report.setReportContent(reportRequest.getReportContent());
        report.setChallengeCode(challenge);
        report.setMemberCode(member);
        report.setReportcategoryCode(reportCategory);
        report.setState(UNPROCESSED);
        report.setCreatedAt(LocalDateTime.now());
        report.setModifiedAt(LocalDateTime.now());

        reportRepository.save(report);
    }


    /* 2. 챌린지 인증 신고 */
    public void reportChallengeCertification(int memberCode, int challengecerCode, ReportRequest reportRequest) {
        ChallengeCertification challengeCertification = challengeCertificationRepository
                .findByChallengecerCode(challengecerCode)
                .orElseThrow(() -> new EntityNotFoundException("Challenge Certification not found with code: " + challengecerCode));

        Member member = (Member) memberRepository.findByMemberCode(memberCode)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with code: " + memberCode));

        ReportCategory reportCategory = reportCategoryRepository.findById(2)
                .orElseThrow(() -> new EntityNotFoundException("신고 카테고리를 찾을 수 없습니다."));

        Report report = new Report();
        report.setReportDate(LocalDate.now());
        report.setReportContent(reportRequest.getReportContent());
//        report.setChallengeCode(null);
        report.setMemberCode(member);
        report.setReportcategoryCode(reportCategory);
        report.setChallengecerCode(challengeCertification);
        report.setState(UNPROCESSED);
        report.setCreatedAt(LocalDateTime.now());
        report.setModifiedAt(LocalDateTime.now());

        reportRepository.save(report);
    }
}
