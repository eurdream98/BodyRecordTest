package A1B1O3.bodyrecord.member.domain.repository;

import A1B1O3.bodyrecord.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
//    List<Body> findAllByMemberCodeMemberCode(final Integer memberCode);
    List<Member> findAll();
    Member findByMemberSocialid(String memberSocialid);

    boolean existsByMemberSocialid(String socialId);

    Optional<Object> findByMemberCode(int memberCode);
}
