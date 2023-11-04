package com.example.structure.member.domain.login.Authentication;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SecureController {

    @GetMapping("/secure")
    public String securePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/body/log/1";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/body/log/1")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void bodyLogPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 여기에 해당 경로에 대한 처리 로직을 작성하세요
        // 인증된 사용자만 접근 가능하도록 @PreAuthorize 어노테이션을 사용했습니다

        // 예시로 "Hello, logged-in user!"라는 메시지를 출력하도록 설정합니다.
        response.getWriter().write("Hello, logged-in user!");
    }

    // 다른 핸들러 메서드와 필요한 로직을 추가로 구현해주세요

}


