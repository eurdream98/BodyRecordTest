package A1B1O3.bodyrecord.report.domain.repository;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import A1B1O3.bodyrecord.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    boolean existsByMemberCodeAndChallengeCode(Member member, Challenge challenge);

    Optional<Report> findByReportCode(int reportCode);

    List<Report> findByReportcategoryCode(int reportcategoryCode);
    List<Report> findByReportcategoryCode(ReportCategory reportCategory);
}
