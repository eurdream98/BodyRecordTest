package A1B1O3.bodyrecord.body.service;

import A1B1O3.bodyrecord.body.domain.Body;
import A1B1O3.bodyrecord.body.domain.repository.BodyRepository;
import A1B1O3.bodyrecord.body.dto.request.BodyRequest;
import A1B1O3.bodyrecord.body.dto.response.BodyResponse;
import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.MemberState;
import com.example.structure.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
   public List<BodyResponse> getAllBodys(final Integer memberCode){
        final List<Body> bodys = bodyRepository.findAllByMemberCodeMemberCode(memberCode);
        return bodys.stream()
                .map(body -> BodyResponse.from(body))
                .collect(Collectors.toList());
    }
    @Transactional
    public Body insert(BodyRequest bodyRequest){

        Member member = new Member();
        member.setMemberCode(1);
        member.setMemberName(member.getMemberName());
        member.setMemberSocialid(member.getMemberSocialid());
//        member.setState(MemberState.ACTIVE);
        member.setState(MemberState.ACTIVE);
        member.setGoalcategoryName(member.getGoalcategoryName());
        member = memberRepository.save(member);
        Body body = Body.of(bodyRequest.getWeight(),bodyRequest.getFat(),bodyRequest.getMuscle(),member);
        return bodyRepository.save(body);
    }

    public void deleteByMemberCode(final Integer memberCode){
        bodyRepository.deleteByMemberCodeMemberCode(memberCode);
    }




}
