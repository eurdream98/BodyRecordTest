package com.example.structure.member.domain.repository;

import java.util.Optional;

import com.example.structure.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberSocialid(String memberSocialid);
}
