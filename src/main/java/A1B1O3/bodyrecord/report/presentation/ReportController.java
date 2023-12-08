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

    @ApiOperation(value = "챌린지 신고", notes = "챌린지를 신고합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
            @ApiImplicitParam(name = "reportRequest", value = "챌린지 신고 요청 정보")
    })
    /* 1. 챌린지 신고 */
    @PostMapping("/{challengeCode}")
    public ResponseEntity<Void> reportChallenge(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengeCode, @RequestBody ReportRequest reportRequest) {
        try {
            reportService.reportChallenge(principalDetails.getMember().getMemberCode(), challengeCode, reportRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "챌린지 인증 신고", notes = "챌린지 인증에 대한 신고를 등록합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengecerCode", value = "챌린지 인증 코드"),
            @ApiImplicitParam(name = "reportRequest", value = "챌린지 인증 신고 요청 정보")
    })
    /* 2. 챌린지 인증 신고 */
    @PostMapping("/certifications/{challengecerCode}")
    public ResponseEntity<Void> reportChallengeCertification(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengecerCode, @RequestBody ReportRequest reportRequest) {

        try {
            reportService.reportChallengeCertification(principalDetails.getMember().getMemberCode(), challengecerCode, reportRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
