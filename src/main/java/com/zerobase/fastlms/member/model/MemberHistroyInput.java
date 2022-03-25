package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class MemberHistroyInput {

    private String userId;
    private LocalDateTime loginDt;
    private String clientIp;
    private String userAgent;

    //== 생성 메서드 ==//
    public static MemberHistroyInput createMemberHistroyInput(String userId
            , String clientIp, String userAgent) {
        MemberHistroyInput memberHistroyInput = new MemberHistroyInput();
        memberHistroyInput.setUserId(userId);
        memberHistroyInput.setLoginDt(LocalDateTime.now());
        memberHistroyInput.setClientIp(clientIp);
        memberHistroyInput.setUserAgent(userAgent);

        return memberHistroyInput;
    }
}
