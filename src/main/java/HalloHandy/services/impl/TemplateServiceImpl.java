package HalloHandy.services.impl;

import HalloHandy.dto.TemplateDto;
import HalloHandy.entity.Template;
import HalloHandy.mappers.TemplateMapper;
import HalloHandy.repository.TemplateRepository;
import HalloHandy.services.TemplateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;
    public List<TemplateDto> getTemplates(){
        List<Template> templates = templateRepository.findAll();
        if (templates.isEmpty()) {
            return null;
        }
        return TemplateMapper.toDTOs(templates);
    }
    public void addTemplate(TemplateDto templateDto){
        Template template = TemplateMapper.toModel(templateDto);
        if (template != null) {
            templateRepository.save(template);
        }
    }
}
