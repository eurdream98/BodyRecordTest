package A1B1O3.bodyrecord.auth;

import A1B1O3.bodyrecord.auth.domain.PrincipalDetails;
import A1B1O3.bodyrecord.auth.jwt.JwtTokenProvider;
import A1B1O3.bodyrecord.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        PrincipalDetails oAuth2User = (PrincipalDetails) authentication.getPrincipal();
        String targetUrl;
        String accessToken =  jwtTokenProvider.createAccessToken(oAuth2User.getMember().getMemberCode());
        String refreshToken =  jwtTokenProvider.createRefreshToken(oAuth2User.getMember().getMemberCode());
        String isFirst = "false";

        authService.registerRefreshToken(oAuth2User.getMember().getMemberCode(), refreshToken);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (oAuth2User.getMember().getMemberNickname() == null) {
            isFirst = "true";
        }
        targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/auth/oauth2/success")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .queryParam("isFirst", isFirst)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);


    }
}
