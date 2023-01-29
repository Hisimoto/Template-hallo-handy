package HalloHandy.controllers;

import HalloHandy.entity.Template;
import HalloHandy.repository.TemplateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TemplateController {

    // standard constructors

    private final TemplateRepository templateRepository;

    public TemplateController(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @GetMapping("/templates")
    public List<Template> getTemplates() {
        return (List<Template>) templateRepository.findAll();
    }

    @PostMapping("/templates")
    void addTemplate(@RequestBody Template template) {
        templateRepository.save(template);
    }
}