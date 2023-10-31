package com.example.structure.goalCateogry.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "goal_category")
@Component
@Getter
@Setter
@NoArgsConstructor
//        (access = PROTECTED)
public class GoalCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goalcategory_code", nullable = false)
    private Integer goalcategoryCode;

    @Column(name = "goalcategory_name", nullable = false, length = 45)
    private String goalcategoryName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    public enum State {
        USEABLE,
        DELETED
    }
    public GoalCategory(Integer goalcategoryCode, String goalcategoryName){
        this.goalcategoryCode=goalcategoryCode;
        this.goalcategoryName=goalcategoryName;
    }
public static GoalCategory of(String goalcategoryName){
        return new GoalCategory(1,goalcategoryName);
}
}
