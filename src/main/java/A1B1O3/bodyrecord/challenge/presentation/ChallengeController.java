package A1B1O3.bodyrecord.challenge.presentation;

import A1B1O3.bodyrecord.challenge.dto.request.ChallengeCertificationRequest;
import A1B1O3.bodyrecord.challenge.dto.request.ChallengeRequest;
import A1B1O3.bodyrecord.challenge.dto.response.*;
import A1B1O3.bodyrecord.challenge.service.ChallengeService;
import A1B1O3.bodyrecord.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    /* 1. 챌린지 카테고리별 조회 */
    @GetMapping("/category/{challengeCategoryCode}")
    public ResponseEntity<List<ChallengeCategoryResponse>> getChallengesByCategory(@PathVariable int challengeCategoryCode) {
        final List<ChallengeCategoryResponse> challengeResponse = challengeService.getChallengesByCategory(challengeCategoryCode);
        return ResponseEntity.ok(challengeResponse);
    }


    /* 2. 챌린지 개별 조회 */
    @GetMapping("/{challengeCode}")
    public ResponseEntity<ChallengeDetailResponse> getChallengeDetails(@PathVariable int challengeCode) {
        ChallengeDetailResponse challengeDetailResponse = challengeService.getChallengeDetails(challengeCode);
        return ResponseEntity.ok(challengeDetailResponse);
    }


    /* 3. 참여중인 챌린지 조회 */
    @GetMapping("/participating/details")
    public ResponseEntity<List<MyChallengeResponse>> getParticipatingChallengeDetails() {
        int memberCode = 1;
        List<MyChallengeResponse> participatingChallengeDetails = challengeService.getParticipatingChallengeDetails(memberCode);
        return ResponseEntity.ok(participatingChallengeDetails);
    }


    /* 4. 챌린지 등록 */
    @PostMapping("/create")
    public ResponseEntity<ChallengeResponse> createChallenge(@RequestBody @Valid ChallengeRequest challengeRequest) {
        int memberCode = 1;
        ChallengeResponse createdChallenge = challengeService.createChallenge(memberCode, challengeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChallenge);
    }


    /* 5. 챌린지 삭제 */
    @DeleteMapping("/{challengeCode}")
    public ResponseEntity<String> deleteChallenge(@PathVariable int challengeCode) {
        int memberCode = 1;
        try {
            challengeService.deleteChallenge(challengeCode, memberCode);
            return ResponseEntity.ok("Challenge with code " + challengeCode + " has been deleted.");
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /* 6. 챌린지 인증 */
    @PostMapping("/certify/{challengeCode}")
    public ResponseEntity<ChallengeCertificationResponse> certifyChallenge(
            @PathVariable int challengeCode,
            @RequestBody @Valid ChallengeCertificationRequest challengeCertificationRequest) {
        int memberCode = 1;
        ChallengeCertificationResponse certifiedChallenge = challengeService.certifyChallenge(challengeCode,memberCode, challengeCertificationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(certifiedChallenge);
    }


    /* 7. 챌린지 인증 조회 */
    @GetMapping("/certifications/{challengeCode}")
    public ResponseEntity<List<ChallengeCertificationResponse>> getChallengeCertifications(@PathVariable int challengeCode) {
        List<ChallengeCertificationResponse> certifications = challengeService.getChallengeCertifications(challengeCode);
        return ResponseEntity.ok(certifications);
    }


    /* 8. 챌린지 인증 삭제 */
    @DeleteMapping("/certifications/{challengecerCode}")
    public ResponseEntity<String> deleteChallengeCertification(@PathVariable int challengecerCode) {
        int memberCode = 1; // 일단
        challengeService.deleteChallengeCertification(challengecerCode, memberCode);
        return ResponseEntity.ok("Your challenge certification with code " + challengecerCode + " has been deleted.");
    }


    /* 9. 챌린지 참여 */
    @PostMapping("/join/{challengeCode}")
    public ResponseEntity<String> joinChallenge(@PathVariable int challengeCode) {
        int memberCode = 1;
        challengeService.joinChallenge(challengeCode, memberCode);
        return ResponseEntity.ok("You have joined the challenge.");
    }


    /* 10. 챌린지 탈퇴 */
    @DeleteMapping("/leave/{challengeCode}")
    public ResponseEntity<String> leaveChallenge(@PathVariable int challengeCode) {
        int memberCode = 1;
        challengeService.leaveChallenge(challengeCode, memberCode);
        return ResponseEntity.ok("You have left the challenge.");
    }


    /* 11. 인기순 챌린지 조회 */
    @GetMapping("/popular-challenges")
    public ResponseEntity<List<ChallengePopularResponse>> getPopularChallenges() {
        List<ChallengePopularResponse> popularChallenges = challengeService.getPopularChallenges();
        return ResponseEntity.ok(popularChallenges);
    }


    /* 12. 신규순 챌린지 조회 */
    @GetMapping("/new-challenges")
    public ResponseEntity<List<ChallengeNewResponse>> getNewChallenges() {
        List<ChallengeNewResponse> newChallenges = challengeService.getChallengesByCreatedAtDesc();
        return ResponseEntity.ok(newChallenges);
    }


    /* 13. 챌린지 달성률 */
    @GetMapping("/achievement-rate/{challengeCode}")
    public ResponseEntity<Double> getChallengeAchievementRate(
            @PathVariable int challengeCode
    ) {
        int memberCode = 1;
        double achievementRate = challengeService.getChallengeAchievementRate(memberCode, challengeCode);
        return ResponseEntity.ok(achievementRate);
    }

}
