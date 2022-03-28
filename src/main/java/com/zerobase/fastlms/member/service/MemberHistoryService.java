package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.MemberHistoryDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.model.MemberHistroyInput;

import java.util.List;

public interface MemberHistoryService {
    /**
     * 로그인 히스토리
     */
    void memberHistorySave(MemberHistroyInput memberHistroyInput);

    /**
     * 로그인 히스토리 조회
     */
    List<MemberHistoryDto> memberHistoryList(MemberParam parameter);
}
