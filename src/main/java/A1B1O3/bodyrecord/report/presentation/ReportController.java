package A1B1O3.bodyrecord.report.presentation;

import A1B1O3.bodyrecord.report.dto.request.ReportRequest;
import A1B1O3.bodyrecord.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    /* 1. 챌린지 신고 */
    @PostMapping("/{challengeCode}")
    public ResponseEntity<String> reportChallenge(@PathVariable int challengeCode, @RequestBody ReportRequest reportRequest) {
        int memberCode = 1;

        try {
            reportService.reportChallenge(memberCode, challengeCode, reportRequest);
            return ResponseEntity.ok("챌린지 신고가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("챌린지 신고 등록 중 오류가 발생했습니다.");
        }
    }


    /* 2. 챌린지 인증 신고 */
    @PostMapping("/certifications/{challengecerCode}")
    public ResponseEntity<String> reportChallengeCertification(@PathVariable int challengecerCode, @RequestBody ReportRequest reportRequest){
        int memberCode = 1;
        try {
            reportService.reportChallengeCertification(memberCode, challengecerCode, reportRequest);
            return ResponseEntity.ok("챌린지 인증 신고가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("챌린지 인증 신고 등록 중 오류가 발생했습니다.");
        }
    }
}
