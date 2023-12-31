package A1B1O3.bodyrecord.member.presentation;

import A1B1O3.bodyrecord.auth.domain.PrincipalDetails;
import A1B1O3.bodyrecord.member.dto.response.MemberResponse;
import A1B1O3.bodyrecord.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/person")
    public List<MemberResponse> getMemberDetail(@AuthenticationPrincipal PrincipalDetails principalDetails){
        final List<MemberResponse> memberResponses = memberService.getMembers(principalDetails.getMember().getMemberCode());
        return memberResponses;
    }

    @GetMapping("/memberCode")
    public int getMemberCode(@AuthenticationPrincipal PrincipalDetails principalDetails){
        final int memberCode = principalDetails.getMember().getMemberCode();
        return memberCode;
    }

}
