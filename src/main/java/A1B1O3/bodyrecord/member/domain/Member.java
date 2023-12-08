package A1B1O3.bodyrecord.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
//        (access = PROTECTED)
@SQLDelete(sql = "UPDATE member SET state = 'DELETED' WHERE id = ?")
@Where(clause = "state = 'ACTIVE'")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int memberCode;

    //jwt
    @Column(nullable = false, length = 30)
    private String memberSocialid;


    @Column(nullable = false, unique = true, length = 20)
    private String memberName;

    @Column
    private String memberImage;

    @Column
    private String memberNickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private MemberState state;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public Member(int memberCode, String memberSocialid, String memberName, String memberNickname) {
        this.memberCode = memberCode;
        this.memberSocialid = memberSocialid;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
    }

    public Member(String memberSocialid, String memberName,String memberNickname,MemberState state, Role role) {
        this.memberSocialid = memberSocialid;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.state = state;
        this.role = role;
    }
    public Member(String memberSocialid, String memberName,MemberState state, Role role) {
        this.memberSocialid = memberSocialid;
        this.memberName = memberName;
        this.state = state;
        this.role = role;
    }

//    public static Member of(String memberSocialid, String memberName,MemberState state, Role role) {
//
//        return new Member(memberSocialid, memberName,state, role);
//    }

    public static Member of(String memberSocialid, String memberName, MemberState state, Role role) {

        return new Member(memberSocialid, memberName,state, role);
    }

    public void updateImageAndNickname(String memberImage,String memberNickname) {
        this.memberImage = memberImage;
        this.memberNickname = memberNickname;
    }


}
