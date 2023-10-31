package A1B1O3.bodyrecord.member.repository;


import A1B1O3.bodyrecord.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByMemberCode(int memberCode);
}

