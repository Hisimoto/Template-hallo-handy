package HalloHandy.services.impl;

import HalloHandy.data.EmailDetails;
import HalloHandy.dto.TemplateDto;
import HalloHandy.entity.Template;
import HalloHandy.mappers.TemplateMapper;
import HalloHandy.repository.TemplateRepository;
import HalloHandy.services.EmailService;
import HalloHandy.services.TemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

  private final TemplateRepository templateRepository;
  private final EmailService emailService;
  private final TemplateMapper templateMapper;

    public TemplateServiceImpl(TemplateRepository templateRepository, EmailService emailService, TemplateMapper templateMapper) {
        this.templateRepository = templateRepository;
        this.emailService = emailService;
        this.templateMapper = templateMapper;
    }

    @Override
    public void addTemplate(TemplateDto templateDTO){

        String templateBody = makeTemplateBody(templateDTO);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setMsgBody(templateBody);
       // emailService.sendSimpleMail(emailDetails);
        Template template = templateMapper.toModel(templateDTO);
        templateRepository.save(template);
    }
    @Override
    public List<TemplateDto> getTemplates() {
        List<Template> dtos = templateRepository.findAll();
        return templateMapper.toDTOs(dtos);
    }

    private String makeTemplateBody (TemplateDto template){
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
