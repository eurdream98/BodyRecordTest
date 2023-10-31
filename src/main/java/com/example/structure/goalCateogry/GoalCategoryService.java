package com.example.structure.goalCateogry;

import com.example.structure.body.domain.Body;
import com.example.structure.body.dto.request.BodyRequest;
import com.example.structure.goalCateogry.domain.GoalCategory;
import com.example.structure.goalCateogry.domain.repostiory.GoalCategoryRepository;
import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.MemberState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class GoalCategoryService {
    private final GoalCategoryRepository goalCategoryRepository;

    public GoalCategoryService(GoalCategoryRepository goalCategoryRepository) {
        this.goalCategoryRepository = goalCategoryRepository;
    }

    @Transactional
    public GoalCategory insert(GoalCategoryRequest goalCategoryRequest){
//        GoalCategory goalCategory = new GoalCategory();
//        goalCategory.setGoalcategoryName("Some Category Name");
//        goalCategory = goalCategoryRepository.save(goalCategory);
//        Member member = new Member();
//        member.setMemberCode(1);
//        member.setMemberAge(30);
//        member.setMemberGender('남');
//        member.setMemberName("고동환");
//        member.setMemberSocialid("eur");
//        member.setState(MemberState.ACTIVE);
//        member.setGoalcategoryCode(goalCategory.getGoalcategoryCode());
//        member = memberRepository.save(member);
        GoalCategory goalCategory = GoalCategory.of(goalCategoryRequest.getGoalCategoryName());
        return goalCategoryRepository.save(goalCategory);
    }

}

