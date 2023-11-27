package A1B1O3.bodyrecord.challenge.domain.repository;


import A1B1O3.bodyrecord.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PUBLIC;


@Entity
@Setter
@Getter
@NoArgsConstructor(access = PUBLIC)
@SQLDelete(sql = "UPDATE challengeParticipate SET state = 'LEAVE' WHERE challengeparticipate_code = ?")
@Where(clause = "state = 'JOIN'")
@EntityListeners(AuditingEntityListener.class)
public class ChallengeParticipate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int ChallengeparticipateCode;

    @Enumerated(value = STRING)
    private ChallengeParticipateState state;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_code")
    private Member memberCode;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "challenge_code")
    private Challenge challengeCode;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate modifiedAt;

    public ChallengeParticipate(
            final int challengeparticipateCode,
            Member memberCode,
            Challenge challengeCode
    ) {
        this.ChallengeparticipateCode = challengeparticipateCode;
        this.memberCode = memberCode;
        this.challengeCode = challengeCode;
        this.createdAt = LocalDate.now();
        this.modifiedAt = LocalDate.now();
        this.state = ChallengeParticipateState.JOIN;
    }

    public void leaveChallenge() {
        this.state = ChallengeParticipateState.LEAVE;
    }

    public Challenge getChallenge() {
        return challengeCode;
    }


}
