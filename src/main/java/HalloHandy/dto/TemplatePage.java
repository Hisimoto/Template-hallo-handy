package HalloHandy.dto;

import HalloHandy.dto.Pageable.Page;
import lombok.Data;

import java.util.List;

@Data
public class TemplatePage extends Page {
    private List<TemplateDto> content;
    private String searchTerm;
}
