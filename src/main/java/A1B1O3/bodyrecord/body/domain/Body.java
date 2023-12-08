package A1B1O3.bodyrecord.body.domain;

import A1B1O3.bodyrecord.body.dto.request.BodyUpdateRequest;
import A1B1O3.bodyrecord.common.BaseEntity;
import A1B1O3.bodyrecord.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import static A1B1O3.bodyrecord.common.type.StatusType.USEABLE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE body SET state = 'DELETED' WHERE body_code = ?")
@Where(clause = "state = 'USEABLE'")
@Table(name = "body")
public class Body extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer bodyCode;

    @Column(nullable = false, name = "weight")
    private float weight;

    @Column(nullable = false)
    private float fat;

    @Column(nullable = false)
    private float muscle;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_code")
    private Member memberCode;


    public Body(float weight,float muscle, float fat, Member memberCode) {
        super(USEABLE);
        this.weight = weight;
        this.muscle = muscle;
        this.fat = fat;
        this.memberCode = memberCode;
    }

    public Body(float weight, float muscle, float fat) {
        super(USEABLE);
        this.weight = weight;
        this.muscle = muscle;
        this.fat = fat;
    }


    public static Body of(float weight, float muscle, float fat,Member member) {
        return new Body(weight, muscle, fat,member);
//       return new Body();
    }
//    public static Body of(float weight, float fat, float muscle,Member member) {
//        return new Body(1, weight, fat, muscle,member);
////       return new Body();
//    }
public static Body of2(float weight, float muscle, float fat,Member member) {
    return new Body(weight, muscle, fat,member);
//       return new Body();
}
    public void update(BodyUpdateRequest bodyUpdateRequest) {
        this.weight = bodyUpdateRequest.getWeight();
        this.muscle = bodyUpdateRequest.getMuscle();
        this.fat = bodyUpdateRequest.getFat();
    }


}
