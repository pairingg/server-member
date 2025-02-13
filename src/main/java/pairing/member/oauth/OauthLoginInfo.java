package pairing.member.oauth;

import org.springframework.http.ResponseEntity;
import pairing.member.common.LoginType;
import pairing.member.entity.Member;

public interface OauthLoginInfo {

    ResponseEntity<String> requestAccessToken(String code);
    OauthTokenDto getAccessToken(ResponseEntity<String> response);
    ResponseEntity<String> requestUserInfo(OauthTokenDto oauthTokenDto);
    Member getUserInfo(ResponseEntity<String> userInfoRes);

    default LoginType type() {
        if(this instanceof KakaoLoginInfo) {
            return LoginType.KAKAO;
        } else if (this instanceof NaverLoginInfo) {
            return LoginType.NAVER;
        } else {
            return LoginType.JWT;
        }
    }
}
