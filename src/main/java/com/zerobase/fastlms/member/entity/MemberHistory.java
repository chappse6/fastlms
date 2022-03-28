package com.zerobase.fastlms.member.entity;

import com.zerobase.fastlms.member.model.MemberHistroyInput;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class MemberHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private LocalDateTime loginDt;
    private String clientIp;
    private String userAgent;

    //== 생성 메서드 ==//
    public static MemberHistory createMemberHistory(MemberHistroyInput memberHistroyInput) {
        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setUserId(memberHistroyInput.getUserId());
        memberHistory.setLoginDt(memberHistroyInput.getLoginDt());
        memberHistory.setClientIp(memberHistroyInput.getClientIp());
        memberHistory.setUserAgent(memberHistroyInput.getUserAgent());

        return memberHistory;
    }
}
