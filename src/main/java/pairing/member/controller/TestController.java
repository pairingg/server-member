package pairing.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pairing.member.entity.CustomUserDetails;
import pairing.member.service.MemberService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TestController {
    @Value("${security.oauth2.client.registration.kakao.client-id}")
    private String kakao_client_id;

    @Value("${security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakao_redirect_uri;

    @Value("${security.oauth2.client.registration.naver.client-id}")
    private String naver_client_id;

    @Value("${security.oauth2.client.registration.naver.client-secret}")
    private String naver_client_secret;

    @Value("${security.oauth2.client.registration.naver.redirect-uri}")
    private String naver_redirect_uri;

    @Value("${security.oauth2.client.registration.naver.authorize-uri}")
    private String getNaver_client_id; // code

    private final MemberService memberService;

    // 로그인 한다고 하면
    // 네이버 로그인 페이지 ( redirect url ) 을 사용자에게 주기
    // 거기로 사용자가 로그인 하면
    // 인가코드 발급 해줌 ( callback url ) 로
//    @GetMapping("login/kakao")
//    public ResponseEntity<String> naverKakao() {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.set("response_type", "code");
//        params.set("client_id", kakao_client_id);
//        params.set("redirect_uri", kakao_redirect_uri);
//        params.set("state", "STATE_STRING");
//
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://kauth.kakao.com/oauth/authorize")
//                .queryParams(params)
//                .encode().build().toUri();
//        return ResponseEntity.status(302).location(uri).build();
//    }

    @GetMapping("/oauth/login/naver")
    public ResponseEntity<String> naverLogin() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("response_type", "code");
        params.set("client_id", naver_client_id);
        params.set("redirect_uri", naver_redirect_uri);
        params.set("state", "STATE_STRING");

        URI uri = UriComponentsBuilder
                .fromUriString(getNaver_client_id)
                .queryParams(params)
                .encode().build().toUri();
        return ResponseEntity.status(302).location(uri).build();
    }
}
