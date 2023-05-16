package HalloHandy.controllers;

import HalloHandy.dto.TemplateDto;
import HalloHandy.repository.TemplateRepository;
import HalloHandy.services.TemplateService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @PostMapping("/export")
    public ResponseEntity<?> getTemplates() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        String fileName = formattedDate + ".xlsx";
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(templateService.getTemplates()));
    }

    @PostMapping("/")
    public void addTemplate(@RequestBody TemplateDto template) {
        templateService.addTemplate(template);
    }
}
