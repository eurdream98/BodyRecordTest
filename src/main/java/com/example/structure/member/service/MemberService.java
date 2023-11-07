package com.example.structure.member.service;

import com.example.structure.body.domain.Body;
import com.example.structure.body.domain.repository.BodyRepository;
import com.example.structure.body.dto.request.BodyRequest;
//import com.example.structure.goalCateogry.domain.GoalCategory;
//import com.example.structure.goalCateogry.domain.repostiory.GoalCategoryRepository;
import com.example.structure.body.dto.response.BodyResponse;
import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.MemberState;
//import com.example.structure.member.domain.login.OAuthService;
//import com.example.structure.member.domain.login.model.GoogleUser;
//import com.example.structure.member.domain.login.model.GoogleUser;
import com.example.structure.member.domain.login.model.GoogleUser;
import com.example.structure.member.domain.repository.MemberRepository;
import com.example.structure.member.dto.request.MemberRequest;
import com.example.structure.member.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public Member insert(GoogleUser googleUser,MemberRequest memberRequest) {
        Member member = Member.of(googleUser, memberRequest);
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> getAllMembers() {
        final List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> MemberResponse.from(member))
                .collect(Collectors.toList());
    }

}
