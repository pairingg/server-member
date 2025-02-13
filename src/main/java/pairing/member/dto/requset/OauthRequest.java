package pairing.member.dto.requset;

import pairing.member.common.LoginType;

public record OauthRequest(String code, LoginType type) {
}