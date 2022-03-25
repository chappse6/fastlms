package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.entity.MemberHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberHistoryDto {

    Long id;

    Member member;
    LocalDateTime loginDt;
    String clientIp;
    String userAgent;

    //추가컬럼
    long totalCount;
    long seq;

    public static MemberHistoryDto of(MemberHistory memberHistory) {

        return  MemberHistoryDto.builder()
                .id(memberHistory.getId())
                .member(memberHistory.getMember())
                .loginDt(memberHistory.getLoginDt())
                .clientIp(memberHistory.getClientIp())
                .userAgent(memberHistory.getUserAgent())
                .build();
    }
}
