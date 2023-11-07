package com.example.structure.member.domain.login;

import com.example.structure.member.domain.Member;
import com.example.structure.member.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountProvider {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Autowired
    public AccountProvider(MemberRepository memberRepository, JwtService jwtService) {
        this.memberRepository=memberRepository;
        this.jwtService = jwtService;
    }



    public int getUserNum(String user_id) {
        Member member = memberRepository.findByMemberSocialid(user_id);
        if (member != null) {
            return member.getMemberCode();
        } else {
            return 0;
        }
    }
}
