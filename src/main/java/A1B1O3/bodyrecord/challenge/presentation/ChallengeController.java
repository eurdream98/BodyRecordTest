package A1B1O3.bodyrecord.challenge.presentation;

import A1B1O3.bodyrecord.auth.domain.PrincipalDetails;
import A1B1O3.bodyrecord.challenge.dto.request.ChallengeCertificationRequest;
import A1B1O3.bodyrecord.challenge.dto.request.ChallengeRequest;
import A1B1O3.bodyrecord.challenge.dto.response.*;
import A1B1O3.bodyrecord.challenge.service.ChallengeService;
import A1B1O3.bodyrecord.common.exception.UnauthorizedException;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"챌린지"})
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;


    @ApiOperation(value = "챌린지 카테고리별 조회", notes = "특정 카테고리의 챌린지 목록을 조회합니다.")
    @ApiImplicitParam(name = "challengeCategoryCode", value = "챌린지 카테고리 코드")
    /* 1. 챌린지 카테고리별 조회 */
    @GetMapping("/category/{challengeCategoryCode}")
    public ResponseEntity<List<ChallengeCategoryResponse>> getChallengesByCategory(@PathVariable int challengeCategoryCode) {
        final List<ChallengeCategoryResponse> challengeResponse = challengeService.getChallengesByCategory(challengeCategoryCode);
        return ResponseEntity.ok(challengeResponse);
    }

    @ApiOperation(value = "챌린지 개별 조회", notes = "특정 챌린지의 상세 정보를 조회합니다.")
    @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드")
    /* 2. 챌린지 개별 조회 */
    @GetMapping("/{challengeCode}")
    public ResponseEntity<ChallengeDetailResponse> getChallengeDetails(@PathVariable int challengeCode) {
        ChallengeDetailResponse challengeDetailResponse = challengeService.getChallengeDetails(challengeCode);
        return ResponseEntity.ok(challengeDetailResponse);
    }

    @ApiOperation(value = "참여중인 챌린지 조회", notes = "현재 사용자가 참여 중인 챌린지 목록을 조회합니다.")
    @ApiImplicitParam(name = "principalDetails", value = "인가된 회원")
    /* 3. 참여중인 챌린지 조회 */
    @GetMapping("/participating/details")
    public ResponseEntity<List<MyChallengeResponse>> getParticipatingChallengeDetails(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<MyChallengeResponse> participatingChallengeDetails = challengeService.getParticipatingChallengeDetails(principalDetails.getMember().getMemberCode());
        return ResponseEntity.ok(participatingChallengeDetails);
    }

    @ApiOperation(value = "챌린지 등록", notes = "챌린지를 등록합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeRequest", value = "챌린지 등록 정보"),
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "챌린지 등록 성공"),
    })
    /* 4. 챌린지 등록 */
    @PostMapping("/create")
    public ResponseEntity<ChallengeResponse> createChallenge(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody @Valid ChallengeRequest challengeRequest) {

        ChallengeResponse createdChallenge = challengeService.createChallenge(principalDetails.getMember().getMemberCode(), challengeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChallenge);
    }

    @ApiOperation(value = "챌린지 삭제", notes = "챌린지를 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 삭제 성공"),
            @ApiResponse(code = 403, message = "챌린지 삭제 권한이 없음"),
            @ApiResponse(code = 404, message = "챌린지가 존재하지 않음"),
    })
    /* 5. 챌린지 삭제 */
    @DeleteMapping("/{challengeCode}")
    public ResponseEntity<String> deleteChallenge(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengeCode) {

        try {
            challengeService.deleteChallenge(challengeCode, principalDetails.getMember().getMemberCode());
            return ResponseEntity.ok("Challenge with code " + challengeCode + " has been deleted.");
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(value = "챌린지 인증", notes = "챌린지를 인증합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
            @ApiImplicitParam(name = "challengeCertificationRequest", value = "챌린지 인증 요청 정보"),
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "챌린지 인증 성공"),
    })
    /* 6. 챌린지 인증 */
    @PostMapping("/certify/{challengeCode}")
    public ResponseEntity<ChallengeCertificationResponse> certifyChallenge(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable int challengeCode,
            @RequestBody @Valid ChallengeCertificationRequest challengeCertificationRequest) {

        ChallengeCertificationResponse certifiedChallenge = challengeService.certifyChallenge(challengeCode, principalDetails.getMember().getMemberCode(), challengeCertificationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(certifiedChallenge);
    }

    @ApiOperation(value = "챌린지 인증 조회", notes = "챌린지의 인증 목록을 조회합니다.")
    @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드")
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 인증 조회 성공"),
    })
    /* 7. 챌린지 인증 조회 */
    @GetMapping("/certifications/{challengeCode}")
    public ResponseEntity<List<ChallengeCertificationResponse>> getChallengeCertifications(@PathVariable int challengeCode) {
        List<ChallengeCertificationResponse> certifications = challengeService.getChallengeCertifications(challengeCode);
        return ResponseEntity.ok(certifications);
    }

    @ApiOperation(value = "챌린지 인증 삭제", notes = "챌린지의 특정 인증을 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengecerCode", value = "챌린지 인증 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 인증 삭제 성공"),
    })
    /* 8. 챌린지 인증 삭제 */
    @DeleteMapping("/certifications/{challengecerCode}")
    public ResponseEntity<String> deleteChallengeCertification(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengecerCode) {

        challengeService.deleteChallengeCertification(challengecerCode, principalDetails.getMember().getMemberCode());
        return ResponseEntity.ok("Your challenge certification with code " + challengecerCode + " has been deleted.");
    }

    @ApiOperation(value = "챌린지 참여", notes = "챌린지에 참여합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 참여 성공"),
    })
    /* 9. 챌린지 참여 */
    @PostMapping("/join/{challengeCode}")
    public ResponseEntity<String> joinChallenge(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengeCode) {

        challengeService.joinChallenge(challengeCode, principalDetails.getMember().getMemberCode());
        return ResponseEntity.ok("You have joined the challenge.");
    }

    @ApiOperation(value = "챌린지 탈퇴", notes = "챌린지에서 탈퇴합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 탈퇴 성공"),
    })
    /* 10. 챌린지 탈퇴 */
    @DeleteMapping("/leave/{challengeCode}")
    public ResponseEntity<String> leaveChallenge(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int challengeCode) {

        challengeService.leaveChallenge(challengeCode, principalDetails.getMember().getMemberCode());
        return ResponseEntity.ok("You have left the challenge.");
    }

    @ApiOperation(value = "인기순 챌린지 조회", notes = "인기순으로 챌린지를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인기순 챌린지 조회 성공"),
    })
    /* 11. 인기순 챌린지 조회 */
    @GetMapping("/popular-challenges")
    public ResponseEntity<List<ChallengePopularResponse>> getPopularChallenges() {
        List<ChallengePopularResponse> popularChallenges = challengeService.getPopularChallenges();
        return ResponseEntity.ok(popularChallenges);
    }

    @ApiOperation(value = "신규순 챌린지 조회", notes = "신규순으로 챌린지를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "신규순 챌린지 조회 성공"),
    })
    /* 12. 신규순 챌린지 조회 */
    @GetMapping("/new-challenges")
    public ResponseEntity<List<ChallengeNewResponse>> getNewChallenges() {
        List<ChallengeNewResponse> newChallenges = challengeService.getChallengesByCreatedAtDesc();
        return ResponseEntity.ok(newChallenges);
    }

    @ApiOperation(value = "챌린지 달성률 조회", notes = "챌린지의 달성률을 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "principalDetails", value = "인가된 회원"),
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 달성률 조회 성공"),
    })
    /* 13. 챌린지 달성률 */
    @GetMapping("/achievement-rate/{challengeCode}")
    public ResponseEntity<Double> getChallengeAchievementRate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable int challengeCode
    ) {
        double achievementRate = challengeService.getChallengeAchievementRate(principalDetails.getMember().getMemberCode(), challengeCode);
        return ResponseEntity.ok(achievementRate);
    }

    @ApiOperation(value = "챌린지 참여 회원수 조회", notes = "챌린지의 현재 참여 회원수를 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "challengeCode", value = "챌린지 코드"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "챌린지 참여 회원수 조회 성공"),
    })
    /* 14. 챌린지의 현재 회원수 */
    @GetMapping("/{challengeCode}/participants-count")
    public ResponseEntity<Integer> getChallengeParticipantsCount(@PathVariable int challengeCode) {
        int participantsCount = challengeService.getChallengeParticipantsCount(challengeCode);
        return ResponseEntity.ok(participantsCount);
    }

}
