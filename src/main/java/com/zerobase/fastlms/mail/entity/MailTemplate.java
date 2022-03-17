package com.zerobase.fastlms.mail.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class MailTemplate implements MailTemplateType {

    @Id
    String mailTemplateId;

    String subject;
    String text;

    // 비지니스 로직
    public String textAdd(String userName, String uuid) {
        return String.format(this.text, userName, uuid);
    }

}
