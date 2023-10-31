package A1B1O3.bodyrecord.member;

import A1B1O3.bodyrecord.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import java.time.LocalDateTime;

import static A1B1O3.bodyrecord.member.MemberState.ACTIVE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;


@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE member SET state = 'DELETED' WHERE id = ?")
@Where(clause = "state = 'ACTIVE'")
public class Member {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_code")
    private int memberCode;

    @Column(name = "member_socialid", nullable = false, length = 30)
    private String memberSocialId;

    @Column(name = "member_nickname", nullable = false, unique = true, length = 20)
    private String memberNickname;

    @Column(name = "member_gender")
    private char memberGender;
    @Column(name = "member_age")
    private int memberAge;
    @Column(name = "member_phone")
    private String memberPhone;
    @Column(name = "member_email")
    private String memberEmail;

    @ManyToOne
    @JoinColumn(name = "goalcategory_code")
    private GoalCategory goalCategory;

    @ManyToOne
    @JoinColumn(name = "body_code")
    private Body body;

    @Enumerated(value = STRING)
    private MemberState state;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public Member(final int memberCode, final String memberSocialId, final String memberNickname, final char memberGender, final int memberAge, final String memberPhone, final String memberEmail, final GoalCategory goalCategory, final Body body) {
        this.memberCode = memberCode;
        this.memberSocialId = memberSocialId;
        this.memberNickname = memberNickname;
        this.memberGender = memberGender;
        this.memberAge = memberAge;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.goalCategory = goalCategory;
        this.body = body;
        this.state = ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
}
