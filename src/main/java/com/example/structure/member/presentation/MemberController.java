package com.example.structure.member.presentation;

import com.example.structure.body.domain.Body;
import com.example.structure.body.domain.repository.BodyRepository;
import com.example.structure.body.dto.request.BodyRequest;
import com.example.structure.body.dto.response.BodyResponse;
import com.example.structure.body.service.BodyService;
//import com.example.structure.goalCateogry.domain.repostiory.GoalCategoryRepository;
//import com.example.structure.member.domain.login.model.GoogleUser;
import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.login.model.GoogleUser;
import com.example.structure.member.domain.repository.MemberRepository;
import com.example.structure.member.dto.request.MemberRequest;
import com.example.structure.member.dto.response.MemberResponse;
import com.example.structure.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> insert(@RequestBody GoogleUser googleUser, @RequestBody MemberRequest memberRequest) {
        System.out.println(googleUser.toString());
        Member member = memberService.insert(googleUser, memberRequest);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMember(){


        final List<MemberResponse> memberResponses = memberService.getAllMembers();
        return ResponseEntity.ok(memberResponses);
    }

}
