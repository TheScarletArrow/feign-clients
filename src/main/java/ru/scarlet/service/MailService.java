package ru.scarlet.service;

import jakarta.mail.MessagingException;
import ru.scarlet.entity.Mail;


public interface MailService {
    void sendMail(Mail mail);

    void sendMailWithAttachments(Mail mail) throws MessagingException;
}
