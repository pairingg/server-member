package pairing.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pairing.member.dto.ProfileDTO;
import pairing.member.dto.response.DrinkAndSmokeResponse;
import pairing.member.entity.CustomUserDetails;
import pairing.member.service.MemberService;
import pairing.member.staticData.entity.HobbyList;
import pairing.member.staticData.repository.HobbyListRepository;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final HobbyListRepository hobbyListRepository;

//    @GetMapping("/profile/region")
//    public Member profile(@RequestBody ProfileCreate profileCreate) {
//
//    }
    @GetMapping("/profile/hobby")
    public List<HobbyList> getHobbyList(){
        return hobbyListRepository.findAll();
    }

    @GetMapping("/profile/drinkandsmoke")
    public DrinkAndSmokeResponse getDrinkAndSmoke(){
        DrinkAndSmokeResponse drinkAndSmokeResponse = new DrinkAndSmokeResponse();
        return drinkAndSmokeResponse;
    }

    @PostMapping("/profile")
    public ResponseEntity<String> postProfile(@RequestBody ProfileDTO profileDTO,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(memberService.postProfile(profileDTO, customUserDetails.getMember().getEmail()));
    }

    @GetMapping("/profile")
    public ProfileDTO getProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return memberService.getProfile(customUserDetails.getMember().getEmail());
    }

    @GetMapping("/profile/{userId}")
    public ProfileDTO getOtherProfile(@PathVariable long userId) {
        return memberService.getOtherProfile(userId);
    }

    @PutMapping("/profile")
    public ResponseEntity<String> putProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                 @RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(memberService.postProfile(profileDTO, customUserDetails.getMember().getEmail()));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(memberService.deleteProfile(customUserDetails.getMember().getEmail()));
    }
}
