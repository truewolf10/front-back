package com.packageBE.PMOStandard.controller;
import com.packageBE.PMOStandard.dto.ProjectDto;
import com.packageBE.PMOStandard.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public Iterable<ProjectDto> getAll(){
        return this.projectService.getAll();
    }

    @GetMapping("/{project_id}")
    public ProjectDto getById(@PathVariable("project_id") Long projectId){
        return this.projectService.getById(projectId);
    }

    @PostMapping()
    public Long addProject(@RequestBody ProjectDto projectDto){
        this.projectService.save(projectDto);

        Long index = 0L; //get the last index generated
        for (ProjectDto i : getAll()){
            System.out.print("\n"+i.getId());
            index = i.getId();
        }
        return index;
    }

    @PutMapping()
    public void update(@RequestBody ProjectDto projectDto){
        this.projectService.save(projectDto);
    }

    @DeleteMapping("/{project_id}")
    public void delete(@PathVariable("project_id") Long projectId){
        this.projectService.remove(projectId);
    }
}
