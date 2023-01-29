package HalloHandy.interfaces;


import HalloHandy.entity.EmailDetails;


public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
