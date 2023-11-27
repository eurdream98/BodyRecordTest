package A1B1O3.bodyrecord.auth.domain.repository;

import A1B1O3.bodyrecord.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
