package A1B1O3.bodyrecord.member.domain.login;

import A1B1O3.bodyrecord.member.domain.login.model.GoogleOAuthToken;
import A1B1O3.bodyrecord.member.domain.login.model.GoogleUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {
    @Autowired
    private RestTemplate restTemplate;
    private final String GOOGLE_SNS_LOGIN_PATH = "/app/accounts/auth/GOOGLE";
    //applications.yml 에서 value annotation을 통해서 값을 받아온다.
    @Value("${spring.security.oauth2.provider.google.authorization-uri}")
    private String GOOGLE_SNS_LOGIN_URL;


    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_SNS_CLIENT_ID;

//    @Value("${spring.OAuth2.google.callback-url}")
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String GOOGLE_SNS_CALLBACK_URL;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;

//    @Value("${spring.security.oauth2.client.registration.google.scope}")
private String[] GOOGLE_DATA_ACCESS_SCOPE = {
        "https://www.googleapis.com/auth/userinfo.email",
        "https://www.googleapis.com/auth/userinfo.profile"
};

    private final ObjectMapper objectMapper;
    @Override
    public String getOauthRedirectURL() {
        String redirectURL = GOOGLE_SNS_LOGIN_URL;

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("scope", String.join(" ", GOOGLE_DATA_ACCESS_SCOPE));
        params.add("response_type", "code");
        params.add("client_id", GOOGLE_SNS_CLIENT_ID);
        params.add("redirect_uri", GOOGLE_SNS_CALLBACK_URL);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(redirectURL);
        builder.queryParams(params);

        return builder.toUriString();
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        String GOOGLE_TOKEN_REQUEST_URL="https://oauth2.googleapis.com/token";
        RestTemplate restTemplate=new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity=restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL,
                params,String.class);

        if(responseEntity.getStatusCode()== HttpStatus.OK){
            return responseEntity;
        }
        return null;
    }

    public GoogleOAuthToken getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
        System.out.println("response.getBody() = " + response.getBody());
        GoogleOAuthToken googleOAuthToken= objectMapper.readValue(response.getBody(), GoogleOAuthToken.class);
        return googleOAuthToken;

    }

    public ResponseEntity<String> requestUserInfo(GoogleOAuthToken oAuthToken) {
        String GOOGLE_USERINFO_REQUEST_URL = "https://www.googleapis.com/oauth2/v1/userinfo";

        // Header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());

        // HttpEntity를 하나 생성해 헤더를 담아서 RestTemplate으로 구글과 통신하게 된다.
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET, request, String.class);

        System.out.println("response.getBody() = " + response.getBody());
        return response;
    }

    public GoogleUser getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException{
        GoogleUser googleUser=objectMapper.readValue(userInfoRes.getBody(), GoogleUser.class);
        return googleUser;
    }

}