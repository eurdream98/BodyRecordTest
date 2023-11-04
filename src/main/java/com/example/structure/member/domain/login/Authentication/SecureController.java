package com.example.structure.member.domain.login.Authentication;

import com.example.structure.body.dto.response.BodyResponse;
import com.example.structure.body.service.BodyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class SecureController {
BodyService bodyService;
    public SecureController(BodyService bodyService) {
        this.bodyService = bodyService;
    }
    @GetMapping("/secure")
    public String securePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/body/log/1";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/secure/body/log/{memberCode}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BodyResponse>> getBodyLog(@PathVariable Integer memberCode) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 로그인한 사용자 정보 가져오기
            String username = authentication.getName();

            // 현재 로그인한 사용자의 체성분 정보 조회
            List<BodyResponse> bodyResponses = bodyService.getAllBodys(memberCode);

            if (bodyResponses.isEmpty()) {
                // 해당 회원의 체성분 정보가 없을 경우 404 Not Found 응답
                return ResponseEntity.notFound().build();
            }

            // 조회된 체성분 정보를 응답으로 반환
            return ResponseEntity.ok(bodyResponses);
        } else {
            // 로그인되지 않은 사용자는 로그인 페이지로 리디렉션
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 다른 핸들러 메서드와 필요한 로직을 추가로 구현해주세요

}
