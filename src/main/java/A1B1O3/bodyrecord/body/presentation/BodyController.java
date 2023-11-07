package A1B1O3.bodyrecord.body.presentation;

import A1B1O3.bodyrecord.body.domain.Body;
import A1B1O3.bodyrecord.body.dto.request.BodyRequest;
import A1B1O3.bodyrecord.body.dto.response.BodyResponse;
import A1B1O3.bodyrecord.body.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/body")
public class BodyController {
    private final BodyService bodyService;
    /*로그인 한 유저의 체성분 모두 조회*/
    @GetMapping("/log")
    public ResponseEntity<List<BodyResponse>> getBody(Integer memberCode){


        final List<BodyResponse> bodyResponses = bodyService.getAllBodys(memberCode);
        return ResponseEntity.ok(bodyResponses);
    }

    @GetMapping("/{memberCode}/latest")
    public List<BodyResponse> getBodyDetail(@PathVariable Integer memberCode) {
        final List<BodyResponse> bodyResponses = bodyService.getAllBodys(memberCode);

        if (bodyResponses.isEmpty()) {
            // bodyResponses가 비어있을 경우 처리할 코드 작성
            // 예외를 던지거나, 기본 값을 반환하거나, 적절한 처리를 수행합니다.
        } else {
            List<BodyResponse> lastBodyResponse = bodyResponses.subList(bodyResponses.size() - 1, bodyResponses.size());
            return lastBodyResponse;
        }
        return bodyResponses;
    }


    @PostMapping
    public ResponseEntity<Body> insert(@RequestBody BodyRequest bodyRequest) {
        Body body = bodyService.insert(bodyRequest);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @DeleteMapping("/{memberCode}")
    public void deleteByMemberCode(@PathVariable Integer memberCode){
        bodyService.deleteByMemberCode(memberCode);
    }

}