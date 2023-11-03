package com.example.structure.member.domain.login.Authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public String securePage() {
        // 로그인한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 로그인한 사용자라면 접근 가능한 페이지로 리디렉션
            return "secure-page";
        } else {
            // 로그인하지 않은 사용자라면 로그인 페이지로 리디렉션
            return "redirect:/login";
        }
    }
}
