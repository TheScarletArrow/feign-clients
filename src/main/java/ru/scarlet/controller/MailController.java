package ru.scarlet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.entity.Mail;
import ru.scarlet.service.MailService;



@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {

private final MailService mailService;

    @PostMapping("/")
    public void sendMail(@RequestBody Mail mail)  {
        if(mail.getAttachments()==null|| mail.getAttachments().isEmpty()){
            mailService.sendMail(mail);
        } else {
            try {
                mailService.sendMailWithAttachments(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
