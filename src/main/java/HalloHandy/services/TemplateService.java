package HalloHandy.services;

import HalloHandy.dto.TemplateDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TemplateService {
    List<TemplateDto> getTemplates();
    void addTemplate(TemplateDto template);
}
