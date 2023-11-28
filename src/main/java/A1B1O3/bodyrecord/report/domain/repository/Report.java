package A1B1O3.bodyrecord.report.domain.repository;

import A1B1O3.bodyrecord.challenge.domain.repository.Challenge;
import A1B1O3.bodyrecord.challenge.domain.repository.ChallengeCertification;
import A1B1O3.bodyrecord.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static A1B1O3.bodyrecord.report.domain.repository.ReportState.UNPROCESSED;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PUBLIC;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PUBLIC)
@SQLDelete(sql = "UPDATE report SET state = 'RETURNED' WHERE report_code = ?")
@Where(clause = "state = 'UNPROCESSED'")
public class Report {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int reportCode;

    @Column
    private LocalDate reportDate;

    @Column
    private String reportContent;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "challenge_code")
    private Challenge challengeCode;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_code")
    private Member memberCode;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reportcategory_code")
    private ReportCategory reportcategoryCode;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Enumerated(value = STRING)
    private ReportState state;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "challengecer_code")
    private ChallengeCertification challengecerCode;

    public Report(final int reportCode,
                     final LocalDate reportDate,
                     final String reportContent,
                     final Challenge challengeCode,
                     final Member memberCode,
                     final ReportCategory reportcategoryCode,
                     final ChallengeCertification challengecerCode
    ) {
        this.reportCode = reportCode;
        this.reportDate = reportDate;
        this.reportContent = reportContent;
        this.challengeCode = challengeCode;
        this.memberCode = memberCode;
        this.reportcategoryCode = reportcategoryCode;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.state = UNPROCESSED;
        this.challengecerCode = challengecerCode;
    }

}
