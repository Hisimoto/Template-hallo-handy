package HalloHandy.interfaces;

import HalloHandy.dto.TemplateDTO;
import HalloHandy.entity.Template;

public interface TemplateService {
    String saveTemplate(TemplateDTO template);
}
