package pairing.member.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pairing.member.common.LoginType;
import pairing.member.dto.requset.OauthRequest;
import pairing.member.dto.response.OauthResponse;
import pairing.member.entity.Member;
import pairing.member.jwt.JwtUtils;
import pairing.member.oauth.OauthLoginInfo;
import pairing.member.oauth.OauthTokenDto;
import pairing.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final List<OauthLoginInfo> oAuth2LoginInfoList;
    private final JwtUtils jwtUtils;
    private final MemberRepository memberRepository;


    public OauthResponse oauthLogin(OauthRequest request, HttpServletResponse response) {
        /**
         * 소셜 로그인 해서 정보 받기
         * 받아서 db에 있으면 통과
         *  없으면 db에 저장 통과
         *  토큰발급
         */
        System.out.println("11111111111");
        OauthLoginInfo oauthLoginInfo = findOAuth2LoginType(request.type());
        System.out.println("222222222222");
        ResponseEntity<String> accessTokenRes = oauthLoginInfo.requestAccessToken(request.code());
        System.out.println("333333333333");
        OauthTokenDto accessTokenDto = oauthLoginInfo.getAccessToken(accessTokenRes);
        System.out.println("4444444444444");
        ResponseEntity<String> stringResponseEntity = oauthLoginInfo.requestUserInfo(accessTokenDto);
        System.out.println("555555555555");
        System.out.println(stringResponseEntity.getBody());
        Member userInfo = oauthLoginInfo.getUserInfo(stringResponseEntity);
        System.out.println(userInfo);
        // 유저 정보 만들었습니다.
        Member savedMember;
        if (!emailExists(userInfo.getEmail())) {
            // 계정이 존재 하지 않으면 만들어서
            savedMember = memberRepository.save(userInfo);
        }
        savedMember = memberRepository.findByEmail(userInfo.getEmail())
                .orElseThrow(RuntimeException::new);
        // 있으면 패스
        String jwt = jwtUtils.generateToken(userInfo.getEmail(), savedMember.getUserId());
        System.out.println(jwt);
        return new OauthResponse(jwt,savedMember.isEnrolled());
    }

    private OauthLoginInfo findOAuth2LoginType(LoginType type) {
        return oAuth2LoginInfoList.stream()
                .filter(x -> x.type() == type)
                .findFirst()
                .get();
    }

    private boolean emailExists(String email) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            return true;
        }
        return false;
    }
}
