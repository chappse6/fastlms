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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Member member;

    private LocalDateTime loginDt;
    private String clientIp;
    private String userAgent;

    //== 생성 메서드 ==//
    public static MemberHistory createMemberHistory(Member member, MemberHistroyInput memberHistroyInput) {
        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setMember(member);
        memberHistory.setLoginDt(memberHistroyInput.getLoginDt());
        memberHistory.setClientIp(memberHistroyInput.getClientIp());
        memberHistory.setUserAgent(memberHistroyInput.getUserAgent());

        return memberHistory;
    }
}
