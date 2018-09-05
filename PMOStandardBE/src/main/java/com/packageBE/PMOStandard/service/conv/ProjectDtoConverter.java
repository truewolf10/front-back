package com.packageBE.PMOStandard.service.conv;

import com.packageBE.PMOStandard.dto.ProjectDto;
import com.packageBE.PMOStandard.dto.ProjectPhaseDto;
import com.packageBE.PMOStandard.model.Project;
import com.packageBE.PMOStandard.model.ProjectPhase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectDtoConverter {

    @Autowired
    ProjectPhaseDtoConverter projectPhaseDtoConverter;

    public ProjectDto toDto(Project project){
        ProjectDto projectDto = localConvertToDto(project);

        List<ProjectPhaseDto> projectPhaseDtos = new ArrayList<>();
        for (ProjectPhase projectPhase : project.getPhases()) {
            projectPhaseDtos.add(projectPhaseDtoConverter.localConvertToDto(projectPhase));
        }

        projectDto.setPhasesDtos(new ArrayList<>(projectPhaseDtos));

        return projectDto;
    }

    ProjectDto localConvertToDto(Project project){
        ProjectDto projectDto = new ProjectDto();

        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setSize(project.getSize());
        projectDto.setDescription(project.getDescription());

        return projectDto;

    }

    public Project toEntity(ProjectDto projectDto){
        Project project = localConvertToEntity(projectDto);

        List<ProjectPhase> projectPhases = new ArrayList<>();
        for(ProjectPhaseDto projectPhaseDto : projectDto.getPhasesDtos()){
            projectPhases.add(projectPhaseDtoConverter.localConvertToEntity(projectPhaseDto));
        }

        project.setPhases(new ArrayList<>(projectPhases));

        return project;
    }

    Project localConvertToEntity(ProjectDto projectDto){
        Project project = new Project();

        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setSize(projectDto.getSize());
        project.setDescription(projectDto.getDescription());

        return project;
    }

}
