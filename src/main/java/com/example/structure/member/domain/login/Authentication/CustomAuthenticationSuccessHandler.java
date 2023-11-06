package com.example.structure.member.domain.login.Authentication;//package com.example.structure.member.domain.login.Authentication;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        // 로그인 성공 후 리디렉션할 URL 설정
//        String redirectUrl = "http://localhost:8080/app/accounts/auth/google/callback";
//        response.sendRedirect(redirectUrl);
//    }
//}