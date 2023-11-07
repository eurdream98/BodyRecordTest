package A1B1O3.bodyrecord.member.domain.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//구글(서드파티)로 액세스 토큰을 보내 받아올 구글에 등록된 사용자 정보
@AllArgsConstructor
@Getter
@Setter
public class GoogleUser {
    public String id;
    public String email;
    public Boolean verifiedEmail;
    public String name;
    public String givenName;
    public String familyName;
    public String picture;
    public String locale;

    @Override
    public String toString() {
        return "GoogleUser{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                // 필요한 다른 필드들을 추가로 포함시킬 수 있습니다.
                '}';
    }
}