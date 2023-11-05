package com.example.structure.member.service;

import com.example.structure.body.domain.Body;
import com.example.structure.body.domain.repository.BodyRepository;
import com.example.structure.body.dto.request.BodyRequest;
import com.example.structure.goalCateogry.domain.GoalCategory;
import com.example.structure.goalCateogry.domain.repostiory.GoalCategoryRepository;
import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.MemberState;
//import com.example.structure.member.domain.login.OAuthService;
//import com.example.structure.member.domain.login.model.GoogleUser;
import com.example.structure.member.domain.repository.MemberRepository;
import com.example.structure.member.dto.request.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class MemberService {
//    private final MemberRepository memberRepository;
//    private final GoalCategoryRepository goalCategoryRepository;
//    @Transactional
//    public Member insert(GoogleUser googleUser) {
//        // GoalCategory 생성 및 저장
//        GoalCategory goalCategory = new GoalCategory();
//        goalCategory.setGoalcategoryName("Some Category Name");
//        goalCategory = goalCategoryRepository.save(goalCategory);
//
//        // Google에서 가져온 사용자 정보로 Member 생성
//        Member member = new Member();
//        member.setMemberSocialid(googleUser.getEmail()); // 구글에서 가져온 사용자 아이디를 설정합니다
//        member.setMemberName(googleUser.getName()); // 구글에서 가져온 사용자 이름을 설정합니다
//        // 나머지 필드들도 적절히 설정해주세요.
//        member.setGoalcategoryCode(goalCategory.getGoalcategoryCode());
//        member.setState(MemberState.ACTIVE);
//
//        // Member 저장
//        return memberRepository.save(member);
//    }
//}
