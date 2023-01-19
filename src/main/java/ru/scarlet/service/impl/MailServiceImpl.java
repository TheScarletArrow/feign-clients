package ru.scarlet.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.scarlet.entity.Mail;
import ru.scarlet.service.MailService;


import java.io.File;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public void sendMail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getBody());
        mailMessage.setFrom(from);

        mailSender.send(mailMessage);
    }

    @Override
    public void sendMailWithAttachments(Mail mail) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("anton20001701@yandex.ru");
        helper.setSubject("Test");
        helper.setText("Test");
        helper.setFrom("testskarlet@yandex.ru");

        for (String it : mail.getAttachments()) {
            FileSystemResource fileSystemResource = new FileSystemResource(new File(it));
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
        }
        javaMailSender.send(message);
    }
}
