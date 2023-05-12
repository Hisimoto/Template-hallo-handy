package HalloHandy.controllers;

import HalloHandy.dto.TemplateDto;
import HalloHandy.repository.TemplateRepository;
import HalloHandy.services.TemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="${api.endpoint}")
public class TemplateController {

    // standard constructors

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/")
    public List<TemplateDto> getTemplates() {
        return  templateService.getTemplates();
    }

    @PostMapping("/")
    public void addTemplate(@RequestBody TemplateDto template) {
        templateService.addTemplate(template);
    }
}
