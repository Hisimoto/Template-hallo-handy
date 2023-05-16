package HalloHandy.services;

import HalloHandy.dto.TemplateDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.InputStream;
import java.util.List;

public interface TemplateService {
    InputStream getTemplates();
    void addTemplate(TemplateDto template);
}
