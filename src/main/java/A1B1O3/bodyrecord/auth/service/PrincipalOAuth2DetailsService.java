package A1B1O3.bodyrecord.auth.service;

import A1B1O3.bodyrecord.auth.domain.PrincipalDetails;
import A1B1O3.bodyrecord.member.domain.Member;
import A1B1O3.bodyrecord.member.domain.MemberState;
import A1B1O3.bodyrecord.member.domain.Role;
import A1B1O3.bodyrecord.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PrincipalOAuth2DetailsService extends DefaultOAuth2UserService{

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String socialId = oAuth2User.getAttributes().get("email").toString();
        String name = oAuth2User.getAttributes().get("name").toString();

        Member member = memberRepository.findByMemberSocialid(socialId);

        if (member == null) {
            log.info("구글 로그인이 최초입니다. 회원가입을 진행합니다.");
            member = Member.of(socialId, name, MemberState.ACTIVE, Role.ROLE_MEMBER);
            memberRepository.save(member);
        }

        return new PrincipalDetails(member);
    }

    public OAuth2User loadUser(int memberCode)  {

        Member member = memberRepository.findById( memberCode)
                .orElseThrow();


        return new PrincipalDetails(member);
    }


}
