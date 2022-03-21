package com.zerobase.fastlms.components;

import com.zerobase.fastlms.mail.entity.MailTemplate;
import com.zerobase.fastlms.mail.repository.MailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MailComponents {
    
    private final JavaMailSender javaMailSender;
    private final MailTemplateRepository mailTemplateRepository;
    
    public void sendMailTest() {
    
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("qkrtp93@naver.com");
        msg.setSubject("안녕하세요. 박세은 입니다.");
        msg.setText(" 안녕하세요. 박세은입니다. 반갑습니다. ");
        
        javaMailSender.send(msg);
    }
    
    public boolean sendMail(String mailTemplateId, String mail, String userName, String uuid) {

        Optional<MailTemplate> optionalMailTemplate = mailTemplateRepository.findById(mailTemplateId);
        if (!optionalMailTemplate.isPresent()) {
            return false;
        }

        MailTemplate mailTemplate = optionalMailTemplate.get();

        boolean result = false;
        
        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(mailTemplate.getSubject());
                mimeMessageHelper.setText(mailTemplate.textFormat(userName, uuid), true);
            }
        };
        
        try {
            javaMailSender.send(msg);
            result = true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    

}
