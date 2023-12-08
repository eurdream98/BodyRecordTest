package A1B1O3.bodyrecord.auth.jwt;

import A1B1O3.bodyrecord.common.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /* 유효한 자격 증명을 제공하지 않고 접근하려 하는 경우 인증 실패이므로 401 오류를 반환한다. */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        System.out.println("exception=" + authException.getClass());
        System.out.println("exception=" + authException.getMessage());
        System.out.println("exception=" + authException.getLocalizedMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ExceptionResponse exceptionResponse = ExceptionResponse.of(HttpStatus.UNAUTHORIZED, "인증 되지 않은 유저입니다.");
        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));

    }

}
