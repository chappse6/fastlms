package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.admin.dto.MemberHistoryDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.MemberHistory;
import com.zerobase.fastlms.member.model.MemberHistroyInput;
import com.zerobase.fastlms.member.repository.MemberHistoryRepository;
import com.zerobase.fastlms.member.service.MemberHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberHistoryServiceImpl implements MemberHistoryService {

    private final MemberHistoryRepository memberHistoryRepository;

    @Transactional
    @Override
    public void memberHistorySave(MemberHistroyInput memberHistroyInput) {

        MemberHistory memberHistory = MemberHistory.createMemberHistory(memberHistroyInput);

        memberHistoryRepository.save(memberHistory);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MemberHistoryDto> memberHistoryList(MemberParam parameter) {

        long totalCount = memberHistoryRepository.countByUserId(parameter.getUserId());
        List<MemberHistoryDto> memberHistoryDtoList = memberHistoryRepository.findAllByUserId(parameter.getUserId());
        if (!CollectionUtils.isEmpty(memberHistoryDtoList)) {
            int i = 0;
            for(MemberHistoryDto x : memberHistoryDtoList) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return memberHistoryDtoList;
    }


}
