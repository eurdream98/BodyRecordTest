package com.example.structure.member.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.structure.body.domain.Body;
import com.example.structure.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    List<Body> findAllByMemberCodeMemberCode(final Integer memberCode);
    List<Member> findAll();
    Member findByMemberSocialid(String memberSocialid);
}
