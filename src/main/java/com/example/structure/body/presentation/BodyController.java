package com.example.structure.body.presentation;

import com.example.structure.body.domain.Body;
import com.example.structure.body.dto.request.BodyRequest;
import com.example.structure.body.dto.response.BodyResponse;
import com.example.structure.body.service.BodyService;
import com.example.structure.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.structure.member.domain.MemberState.ACTIVE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/body/log")
public class BodyController {
    private final BodyService bodyService;
    /*로그인 한 유저의 체성분 모두 조회*/
    @GetMapping("/{memberCode}")
    public ResponseEntity<List<BodyResponse>> getBody(@PathVariable Integer memberCode){


        final List<BodyResponse> bodyResponses = bodyService.getAllBodys(memberCode);
        return ResponseEntity.ok(bodyResponses);
    }

}
