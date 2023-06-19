package HalloHandy.controllers;

import HalloHandy.dto.TemplateDto;
import HalloHandy.dto.Pageable.Page;
import HalloHandy.dto.TemplatePage;
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
    public ResponseEntity<?> exportAll() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        String fileName = formattedDate + ".xlsx";
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(templateService.exportAll()));
    }

    @PostMapping("/")
    public TemplateDto addTemplate(@RequestBody TemplateDto template) {
        return templateService.addTemplate(template);
    }

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }
    @PutMapping("/resolve")
    public ResponseEntity<?> resolveTemplate(@RequestBody Long id) throws Exception {
        return ResponseEntity.ok(templateService.resolveTemplate(id));
    }
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        templateService.deleteById(id);
    }
    @GetMapping("/{id}")
    public TemplateDto getRequest(@PathVariable Long id) {
        return templateService.getById(id);
    }
    @PostMapping("/search")
    public TemplatePage getTemplatesPaginatedBySearchTerm(@RequestBody TemplatePage page) {
        return templateService.getTemplatesPaginatedBySearchTerm(page);
    }
}
