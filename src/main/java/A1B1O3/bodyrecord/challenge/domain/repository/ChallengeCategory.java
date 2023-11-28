package A1B1O3.bodyrecord.challenge.domain.repository;

import A1B1O3.bodyrecord.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static A1B1O3.bodyrecord.common.type.StatusType.USEABLE;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE challengeCategory SET state = 'DELETED' WHERE challengecategory_code = ?")
@Where(clause = "state = 'USEABLE'")
public class ChallengeCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int challengecategoryCode;

    @Column(nullable = false, length = 100)
    private String challengecategoryName;

    public ChallengeCategory(final int challengecategoryCode,
                     final String challengecategoryName
    ) {
        super(USEABLE);
        this.challengecategoryCode = challengecategoryCode;
        this.challengecategoryName = challengecategoryName;
    }
}
