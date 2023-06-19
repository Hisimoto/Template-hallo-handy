package HalloHandy.services;

import HalloHandy.dto.Pageable.Page;
import HalloHandy.dto.TemplateDto;
import HalloHandy.dto.TemplatePage;
import HalloHandy.entity.Template;

import java.io.InputStream;
import java.util.List;

public interface TemplateService {
    InputStream exportAll();
    TemplateDto addTemplate(TemplateDto template);
    TemplateDto resolveTemplate(Long id) throws Exception;
    TemplatePage getTemplatesPaginatedBySearchTerm( TemplatePage page);
    void deleteById(Long id);
    TemplateDto getById(Long id);
}
