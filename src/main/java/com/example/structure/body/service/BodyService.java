package com.example.structure.body.service;

import com.example.structure.body.domain.Body;
import com.example.structure.body.domain.repository.BodyRepository;
import com.example.structure.body.dto.request.BodyRequest;
import com.example.structure.body.dto.response.BodyResponse;
import com.example.structure.goalCateogry.domain.GoalCategory;
import com.example.structure.goalCateogry.domain.repostiory.GoalCategoryRepository;
import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.MemberState;
import com.example.structure.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BodyService {

    private final BodyRepository bodyRepository;
    private final MemberRepository memberRepository;
private final GoalCategoryRepository goalCategoryRepository;
    @Transactional(readOnly = true)
   public List<BodyResponse> getAllBodys(final Integer memberCode){
        final List<Body> bodys = bodyRepository.findAllByMemberCodeMemberCode(memberCode);
        return bodys.stream()
                .map(body -> BodyResponse.from(body))
                .collect(Collectors.toList());
    }
//


}
