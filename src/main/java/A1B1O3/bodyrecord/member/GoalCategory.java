package A1B1O3.bodyrecord.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class GoalCategory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "goalcategory_code")
    private int goalCategoryCode;

    @Column(name = "goalcategory_name")
    private String goalCategoryName;
}
