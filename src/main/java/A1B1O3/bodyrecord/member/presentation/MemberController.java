package A1B1O3.bodyrecord.member.presentation;

import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.member.dto.request.MemberRequest;
import A1B1O3.bodyrecord.member.dto.response.MemberResponse;
import A1B1O3.bodyrecord.member.service.MemberService;
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

//    @PostMapping
//    public ResponseEntity<Member> insert(@RequestBody GoogleUser googleUser, @RequestBody MemberRequest memberRequest) {
//        System.out.println(googleUser.toString());
//        Member member = memberService.insert(googleUser, memberRequest);
//        return new ResponseEntity<>(member, HttpStatus.CREATED);
//    }
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMember(){


        final List<MemberResponse> memberResponses = memberService.getAllMembers();
        return ResponseEntity.ok(memberResponses);
    }

}
