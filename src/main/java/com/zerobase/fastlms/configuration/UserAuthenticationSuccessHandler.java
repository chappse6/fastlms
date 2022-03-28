package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.model.MemberHistroyInput;
import com.zerobase.fastlms.member.service.MemberHistoryService;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberHistoryService memberHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {

        String clinetIp = RequestUtils.getClientIP(request);
        String userAgent = RequestUtils.getUserAgent(request);

        String userId = authentication.getName();

        MemberHistroyInput memberHistroyInput = MemberHistroyInput
                .createMemberHistroyInput(userId, clinetIp, userAgent);

        memberHistoryService.memberHistorySave(memberHistroyInput);

        response.sendRedirect("/");
    }
}
