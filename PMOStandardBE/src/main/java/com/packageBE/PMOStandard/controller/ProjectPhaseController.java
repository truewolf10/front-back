package com.packageBE.PMOStandard.controller;

import com.packageBE.PMOStandard.dto.ProjectPhaseDto;
import com.packageBE.PMOStandard.dto.ProjectPhaseTemplateDto;
import com.packageBE.PMOStandard.service.IProjectPhaseService;
import com.packageBE.PMOStandard.service.IProjectPhaseTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/phase")
public class ProjectPhaseController  {


    private final IProjectPhaseService iProjectPhaseService;
    private final IProjectPhaseTemplateService templateService;

    public ProjectPhaseController(IProjectPhaseService iProjectPhaseService, IProjectPhaseTemplateService templateService) {
        this.iProjectPhaseService = iProjectPhaseService;
        this.templateService = templateService;
    }

    @GetMapping
    public List<ProjectPhaseDto> getAll(){
        return this.iProjectPhaseService.getAll();
    }

    @GetMapping("/{projectPhase_id}")
    public ProjectPhaseDto getById(@PathVariable("projectPhase_id") Long projectPhaseId){
        return iProjectPhaseService.getById(projectPhaseId);
    }

    @GetMapping("/temp/{projectPhase_id}")
    public List<ProjectPhaseTemplateDto> getByIdTemplate(@PathVariable("projectPhase_id") Long projectPhaseId){
       return iProjectPhaseService.getById(projectPhaseId).getProjectPhaseTemplates();

    }

    @PostMapping("/params")
    public Long addProjectPhaseWithTemplate(@RequestBody ProjectPhaseDto projectPhaseDto,@RequestParam(value = "id") List<Long> listTempId){
        List<ProjectPhaseTemplateDto> projectPhaseTemplates = new ArrayList<>();
        for (int i=0; i<listTempId.size(); i++){
            ProjectPhaseTemplateDto a = new ProjectPhaseTemplateDto(templateService.getById(listTempId.get(i)).getId(),
                    templateService.getById(listTempId.get(i)).getName(),
                    templateService.getById(listTempId.get(i)).getUrl(),
                    templateService.getById(listTempId.get(i)).getDescription(),
                    templateService.getById(listTempId.get(i)).getDocument(),
                    templateService.getById(listTempId.get(i)).getDocumentName());
            projectPhaseTemplates.add(a);
        }
        projectPhaseDto.setProjectPhaseTemplates(projectPhaseTemplates);
        iProjectPhaseService.add(projectPhaseDto);

        Long index = 0L;
        for(ProjectPhaseDto phaseDto : getAll()) {
            index = phaseDto.getId();
        }

        return index;
    }

    @DeleteMapping("/{projectPhase_id}")
    public void deleteProjectPhase(@PathVariable("projectPhase_id") Long projectPhaseId){
        iProjectPhaseService.delete(getById(projectPhaseId));
    }

    @PutMapping("/params")
    public void  update(@RequestBody ProjectPhaseDto projectPhaseDto, @RequestParam(value="id") List<Long> listTempId){
        List<ProjectPhaseTemplateDto> projectPhaseTemplates = new ArrayList<>();
        for (int i=0; i<listTempId.size(); i++){
            ProjectPhaseTemplateDto a = new ProjectPhaseTemplateDto(templateService.getById(listTempId.get(i)).getId(),
                    templateService.getById(listTempId.get(i)).getName(),
                    templateService.getById(listTempId.get(i)).getUrl(),
                    templateService.getById(listTempId.get(i)).getDescription(),
                    templateService.getById(listTempId.get(i)).getDocument(),
                    templateService.getById(listTempId.get(i)).getDocumentName());
            projectPhaseTemplates.add(a);
        }
        projectPhaseDto.setProjectPhaseTemplates(projectPhaseTemplates);
        iProjectPhaseService.add(projectPhaseDto);
    }

    @PutMapping
    public void updateStatus(@RequestBody ProjectPhaseDto projectPhaseDto){
        iProjectPhaseService.add(projectPhaseDto);
    }

}
