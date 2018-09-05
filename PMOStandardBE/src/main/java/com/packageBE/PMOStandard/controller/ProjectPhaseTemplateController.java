package com.packageBE.PMOStandard.controller;

import com.packageBE.PMOStandard.dto.ProjectPhaseTemplateDto;
import com.packageBE.PMOStandard.service.IProjectPhaseTemplateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/phase-template")
public class ProjectPhaseTemplateController {

    private final IProjectPhaseTemplateService templateService;

    public ProjectPhaseTemplateController(IProjectPhaseTemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping()
    Long save(@RequestBody ProjectPhaseTemplateDto templateDto) {
        return templateService.save(templateDto);
    }

    @PutMapping()
    Long update(@RequestBody ProjectPhaseTemplateDto templateDto) {
        return templateService.save(templateDto);
    }

    @DeleteMapping("/{template_id}")
    Boolean delete(@PathVariable("template_id") Long projectPhaseTemplateId){return templateService.delete(projectPhaseTemplateId);}

    @GetMapping("/{id}")
    ProjectPhaseTemplateDto getById(@PathVariable("id") Long id) {
        return templateService.getById(id);
    }

    @GetMapping()
    List<ProjectPhaseTemplateDto> getAll() {
        return templateService.getAll();
    }

    @PostMapping("/upload")
    Long upload(@RequestParam("file") MultipartFile file,
                @RequestParam(name = "id", required = false) Long id,
                @RequestParam(name = "name", required = false) String name,
                @RequestParam(name = "url", required = false) String url,
                @RequestParam(name = "description", required = false) String description) throws IOException {

        ProjectPhaseTemplateDto templateDto = new ProjectPhaseTemplateDto(id, name, url, description, file.getBytes(), file.getOriginalFilename());
        templateService.save(templateDto);

        Long index = 0L;
        for(ProjectPhaseTemplateDto temDto : getAll()) {
            index = temDto.getId();
        }

        return index;
    }

    @GetMapping("/download/{id}")
    byte[] download(@PathVariable("id") Long id) {
        return templateService.getById(id).getDocument();
    }

}
