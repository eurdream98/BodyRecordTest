package A1B1O3.bodyrecord.member.domain.repository;

import A1B1O3.bodyrecord.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    List<Body> findAllByMemberCodeMemberCode(final Integer memberCode);
    List<Member> findAll();
    Member findByMemberSocialid(String memberSocialid);
}
