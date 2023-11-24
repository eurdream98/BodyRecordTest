package A1B1O3.bodyrecord.body.service;

import A1B1O3.bodyrecord.auth.domain.PrincipalDetails;
import A1B1O3.bodyrecord.body.domain.Body;
import A1B1O3.bodyrecord.body.domain.repository.BodyRepository;
import A1B1O3.bodyrecord.body.dto.request.BodyRequest;
import A1B1O3.bodyrecord.body.dto.response.BodyResponse;
import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.member.domain.MemberState;
import A1B1O3.bodyrecord.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BodyService {

    private final BodyRepository bodyRepository;
    private final MemberRepository memberRepository;
    @Transactional(readOnly = true)
   public List<BodyResponse> getAllBodys(final Long memberCode){
        final List<Body> bodys = bodyRepository.findAllByMemberCodeMemberCode(memberCode);
        return bodys.stream()
                .map(body -> BodyResponse.from(body))
                .collect(Collectors.toList());
    }
    @Transactional
    public Body insert(BodyRequest bodyRequest,@AuthenticationPrincipal PrincipalDetails principalDetails){

        Member member = new Member();
        member.setMemberCode(principalDetails.getMember().getMemberCode());
        member.setMemberName(principalDetails.getMember().getMemberName());
        member.setMemberSocialid(principalDetails.getMember().getMemberSocialid());
        member.setMemberNickname(principalDetails.getMember().getMemberNickname());
//        member.setState(MemberState.ACTIVE);
        member.setState(MemberState.ACTIVE);
        member = memberRepository.save(member);


        Body body = Body.of(bodyRequest.getWeight(),bodyRequest.getFat(),bodyRequest.getMuscle(),member);
        return bodyRepository.save(body);
    }



    public void deleteByMemberCode(final Long memberCode){
        bodyRepository.deleteByMemberCodeMemberCode(memberCode);
    }




}
