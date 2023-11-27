package A1B1O3.bodyrecord.auth.service;

import A1B1O3.bodyrecord.auth.domain.Token;
import A1B1O3.bodyrecord.auth.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final TokenRepository tokenRepository;

    public Long registerRefreshToken(int memberCode, String refreshToken) {

        Optional<Token> token = tokenRepository.findById( memberCode);
        if(token.isPresent()) {
            token.get().updateRefreshToken(refreshToken);
        } else {
            tokenRepository.save(Token.of(memberCode, refreshToken));
        }

        return (long) memberCode;
    }

}
