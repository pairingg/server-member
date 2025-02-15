package pairing.member.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pairing.member.dto.requset.OauthRequest;
import pairing.member.dto.response.OauthResponse;
import pairing.member.jwt.JwtUtils;
import pairing.member.service.AuthService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/oauth/login")
    public OauthResponse login(@RequestBody OauthRequest request, HttpServletResponse response) {
        System.out.println(request.toString());
        OauthResponse oauthResponse = authService.oauthLogin(request, response);
        System.out.println(oauthResponse.toString());
        System.out.println(oauthResponse);
        return oauthResponse;
    }

    @GetMapping("/oauth/token")
    public boolean getToken(@RequestParam String token) {
        return jwtUtils.validateToken(token);
    }
}
