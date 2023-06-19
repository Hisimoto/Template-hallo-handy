package HalloHandy.services.impl;

import HalloHandy.data.EmailDetails;
import HalloHandy.dto.TemplateDto;
import HalloHandy.dto.TemplatePage;
import HalloHandy.entity.Template;
import HalloHandy.entity.QTemplate;
import HalloHandy.enums.Status;
import HalloHandy.mappers.TemplateMapper;
import HalloHandy.repository.TemplateRepository;
import HalloHandy.services.EmailService;
import HalloHandy.services.TemplateService;
import HalloHandy.util.ExportUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.querydsl.core.BooleanBuilder;
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
    public TemplateDto addTemplate(TemplateDto templateDTO){

        String templateBody = makeTemplateBody(templateDTO);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setMsgBody(templateBody);
       // emailService.sendSimpleMail(emailDetails);
        Template template = templateMapper.toModel(templateDTO);
        template = templateRepository.save(template);
        return templateMapper.toDTO(template);
    }

    @Override
    public TemplateDto resolveTemplate(Long id) throws Exception {
        Template template = templateRepository.getTemplateById(id);
        if (template.getStatus() == null) {
            template.setStatus(Status.RESOLVED);
        } else if(template.getStatus().equals(Status.NOTRESOLVED) ) {
            template.setStatus(Status.RESOLVED);

        } else {
            template.setStatus(Status.NOTRESOLVED);
        }
        template = templateRepository.save(template);
        return templateMapper.toDTO(template);
    }

    @Override
    public TemplatePage getTemplatesPaginatedBySearchTerm(TemplatePage templatePage) {
        Pageable pageable = templatePage.getPageableObject();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (templatePage.getSearchTerm() != null) {
            booleanBuilder = this.buildQuery(templatePage.getSearchTerm());
        }
        Page<Template> templates = templateRepository.findAll(booleanBuilder,pageable);
        List<TemplateDto> templateDtos = templateMapper.toDTOs(templates.getContent());
        templatePage.setContent(templateDtos);
        templatePage.setTotalPages(templates.getTotalPages());
        templatePage.setTotalElements(templates.getTotalElements());
        return templatePage;
    }

    @Override
    public void deleteById(Long id) {
    templateRepository.deleteById(id);
    }

    @Override
    public TemplateDto getById(Long id) {
        Template template = templateRepository.getTemplateById(id);
        return templateMapper.toDTO(template);
    }

    @Override
    public InputStream exportAll() {
        List<Template> templates = templateRepository.findAllForExport();
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

    private BooleanBuilder buildQuery(String searchTerm){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QTemplate qTemplate = QTemplate.template;

        if(searchTerm != null && !searchTerm.isEmpty()){
            booleanBuilder.and(qTemplate.name.containsIgnoreCase(searchTerm)
                    .or(qTemplate.vorname.containsIgnoreCase(searchTerm))
                    .or(qTemplate.email.containsIgnoreCase(searchTerm))
                    .or(qTemplate.telefon.containsIgnoreCase(searchTerm))
                    .or(qTemplate.hertseller.containsIgnoreCase(searchTerm))
                    .or(qTemplate.modell.containsIgnoreCase(searchTerm))
                    .or(qTemplate.modell.containsIgnoreCase(searchTerm))
            );
        }
        return booleanBuilder;

    }

}
