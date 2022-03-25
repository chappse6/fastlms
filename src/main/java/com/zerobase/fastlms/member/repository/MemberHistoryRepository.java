package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.admin.dto.MemberHistoryDto;
import com.zerobase.fastlms.member.entity.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, String> {
    long countByMember_UserId(String userId);
    List<MemberHistoryDto> findAllByMember_UserId(String userId);

}
