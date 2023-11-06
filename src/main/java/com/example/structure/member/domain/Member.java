package com.example.structure.member.domain;

import static com.example.structure.member.domain.MemberState.ACTIVE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;

import com.example.structure.body.domain.Body;
import com.example.structure.member.domain.login.model.GoogleUser;
import com.example.structure.member.dto.request.MemberRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
//        (access = PROTECTED)
@SQLDelete(sql = "UPDATE member SET state = 'DELETED' WHERE id = ?")
@Where(clause = "state = 'ACTIVE'")
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer memberCode;

    //jwt
    @Column(nullable = false, length = 30)
    private String memberSocialid;


    @Column(nullable = false, unique = true, length = 20)
    private String memberName;

    @Column(nullable = false)
    private String memberNickname;

    @Column(nullable = false)
    private String goalcategoryName;


    @Enumerated(EnumType.STRING)
    private MemberState state;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public Member(Integer memberCode, String memberSocialid, String memberName, String memberNickname, String goalcategoryName) {
        this.memberCode = memberCode;
        this.memberSocialid = memberSocialid;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.goalcategoryName = goalcategoryName;
    }
    public static Member of(GoogleUser googleUser, MemberRequest memberRequest) {
        Member member = new Member();
        member.setMemberSocialid(googleUser.getEmail());
        member.setMemberName(googleUser.getName());
        member.setMemberNickname(memberRequest.getMemberNickname());
        member.setGoalcategoryName(memberRequest.getGoalcategoryName());
        member.setState(MemberState.ACTIVE);
        // createdAt 및 modifiedAt 설정 등 필요한 로직 추가
        return member;
    }
}
