package A1B1O3.bodyrecord.auth.domain;

import A1B1O3.bodyrecord.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class PrincipalDetails implements OAuth2User {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole().toString();
            }
        });
        return authorities;
    }

    @Override
    public String getName() {
        return member.getMemberName();
    }


    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
}
