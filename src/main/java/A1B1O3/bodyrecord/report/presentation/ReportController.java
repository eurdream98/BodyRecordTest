package A1B1O3.bodyrecord.report.presentation;

import A1B1O3.bodyrecord.auth.domain.PrincipalDetails;
import A1B1O3.bodyrecord.report.dto.request.ReportRequest;
import A1B1O3.bodyrecord.report.service.ReportService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"신고"})
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;


    @ApiOperation(value = "챌린지 달성률 조회", notes = "챌린지의 달성률을 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 달성률 조회 성공"),
    })
    /* 1. 챌린지 신고 */
    @PostMapping("/{challengeCode}")
    public ResponseEntity<String> reportChallenge(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengeCode, @RequestBody ReportRequest reportRequest) {

        try {
            reportService.reportChallenge(principalDetails.getMember().getMemberCode(), challengeCode, reportRequest);
            return ResponseEntity.ok("챌린지 신고가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("챌린지 신고 등록 중 오류가 발생했습니다.");
        }
    }

    @ApiOperation(value = "챌린지 참여 회원수 조회", notes = "챌린지의 현재 참여 회원수를 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 참여 회원수 조회 성공"),
    })
    /* 2. 챌린지 인증 신고 */
    @PostMapping("/certifications/{challengecerCode}")
    public ResponseEntity<String> reportChallengeCertification(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengecerCode, @RequestBody ReportRequest reportRequest){

        try {
            reportService.reportChallengeCertification(principalDetails.getMember().getMemberCode(), challengecerCode, reportRequest);
            return ResponseEntity.ok("챌린지 인증 신고가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("챌린지 인증 신고 등록 중 오류가 발생했습니다.");
        }
    }
}
