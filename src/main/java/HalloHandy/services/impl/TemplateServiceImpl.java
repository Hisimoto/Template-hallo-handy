package HalloHandy.services.impl;

import HalloHandy.data.EmailDetails;
import HalloHandy.dto.TemplateDto;
import HalloHandy.entity.Template;
import HalloHandy.mappers.TemplateMapper;
import HalloHandy.repository.TemplateRepository;
import HalloHandy.services.EmailService;
import HalloHandy.services.TemplateService;
import HalloHandy.util.ExportUtil;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

  private final TemplateRepository templateRepository;
  private final EmailService emailService;
  private final TemplateMapper templateMapper;
  private final ExportUtil exportUtil;

    public TemplateServiceImpl(TemplateRepository templateRepository, EmailService emailService, TemplateMapper templateMapper, ExportUtil exportUtil) {
        this.templateRepository = templateRepository;
        this.emailService = emailService;
        this.templateMapper = templateMapper;
        this.exportUtil = exportUtil;
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
    public InputStream getTemplates() {
        List<Template> templates = templateRepository.findAll();
        return new ByteArrayInputStream(exportUtil.exportTemplatesInfo(templateMapper.toDTOs(templates)));
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
