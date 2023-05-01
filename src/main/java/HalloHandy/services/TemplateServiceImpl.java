package HalloHandy.services;

import HalloHandy.dto.TemplateDTO;
import HalloHandy.entity.EmailDetails;
import HalloHandy.entity.Template;
import HalloHandy.interfaces.EmailService;
import HalloHandy.interfaces.TemplateService;
import HalloHandy.repository.TemplateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

  private final TemplateRepository templateRepository;
  private final EmailService emailService;
  @Autowired
  private final ModelMapper modelMapper;

    public TemplateServiceImpl(TemplateRepository templateRepository, EmailService emailService, ModelMapper modelMapper) {
        this.templateRepository = templateRepository;
        this.emailService = emailService;
        this.modelMapper = modelMapper;
    }

    public String saveTemplate(TemplateDTO templateDTO){

        String templateBody = makeTemplateBody(templateDTO);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setMsgBody(templateBody);
        emailService.sendSimpleMail(emailDetails);
        Template template = modelMapper.map(templateDTO, Template.class);
        templateRepository.save(template);


        return "ok";
    }

    public String makeTemplateBody (TemplateDTO template){
        String templateBody = new String();

        StringBuilder sb = new StringBuilder(templateBody);

        sb.append("Acesta este un mail \n");
        sb.append("creat cu stringbuilder \n");
        sb.append("folosind variabila email: ");
        sb.append(template.getEmail());
        sb.append(" si nume: ");
        sb.append(template.getName());
        sb.append("\n");
        sb.append("");
        sb.append("");
        sb.append("");

        return sb.toString();

    }

}
