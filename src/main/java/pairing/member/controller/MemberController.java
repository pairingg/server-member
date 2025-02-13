package pairing.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pairing.member.common.Smoking;
import pairing.member.dto.requset.ProfileCreate;
import pairing.member.dto.response.DrinkAndSmokeResponse;
import pairing.member.entity.CustomUserDetails;
import pairing.member.entity.Member;
import pairing.member.service.MemberService;
import pairing.member.staticData.entity.HobbyList;
import pairing.member.staticData.repository.HobbyListRepository;

import java.security.Principal;
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
    public ResponseEntity<String> postProfile(@RequestBody ProfileCreate profileCreate,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String s = memberService.postProfile(profileCreate, customUserDetails.getMember().getEmail());
        return ResponseEntity.ok(s);
    }
}
