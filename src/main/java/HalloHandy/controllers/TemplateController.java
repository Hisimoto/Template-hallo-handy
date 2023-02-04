package HalloHandy.controllers;

import HalloHandy.dto.TemplateDTO;
import HalloHandy.entity.Template;
import HalloHandy.interfaces.TemplateService;
import HalloHandy.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TemplateController {

    // standard constructors
    @Autowired private TemplateService templateService;

    private final TemplateRepository templateRepository;

    public TemplateController(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @GetMapping("/templates")
    public List<Template> getTemplates() {
        return (List<Template>) templateRepository.findAll();
    }

    @PostMapping("/template")
    void addTemplate(@RequestBody TemplateDTO template) {

        templateService.saveTemplate(template);
        }
}