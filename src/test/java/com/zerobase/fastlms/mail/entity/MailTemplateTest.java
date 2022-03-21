package com.zerobase.fastlms.mail.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class MailTemplateTest {

    @Test
    void textAdd메서드테스트() {
        //given
        MailTemplate mailTemplate = new MailTemplate();
        mailTemplate.setText("<p>%s님 fastlms 비밀번호 초기화 메일 입니다.<p>\n" +
                "<p>아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요.</p>\n" +
                "<div>\n" +
                "<a target='_blank' href='http://localhost:8080/member/reset/password?id=%s'>비밀번호 초기화 링크\n" +
                "</a>\n" +
                "</div>");
        String userName = "test";
        String uuid = "test";

        //whet
        String text = mailTemplate.textFormat(userName, uuid);

        //then
        assertThat(text).isEqualTo("<p>test님 fastlms 비밀번호 초기화 메일 입니다.<p>\n" +
                "<p>아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요.</p>\n" +
                "<div>\n" +
                "<a target='_blank' href='http://localhost:8080/member/reset/password?id=test'>비밀번호 초기화 링크\n" +
                "</a>\n" +
                "</div>");
    }
}