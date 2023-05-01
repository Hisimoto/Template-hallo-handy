package HalloHandy.services;


import HalloHandy.data.EmailDetails;


public interface EmailService {


    String sendSimpleMail(EmailDetails details);


    String sendMailWithAttachment(EmailDetails details);
}
