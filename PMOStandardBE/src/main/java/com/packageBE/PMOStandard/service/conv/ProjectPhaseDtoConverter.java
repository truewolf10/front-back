package com.packageBE.PMOStandard.service.conv;

import com.packageBE.PMOStandard.dto.ProjectDto;
import com.packageBE.PMOStandard.dto.ProjectPhaseDto;
import com.packageBE.PMOStandard.dto.ProjectPhaseTemplateDto;
import com.packageBE.PMOStandard.model.Project;
import com.packageBE.PMOStandard.model.ProjectPhase;
import com.packageBE.PMOStandard.model.ProjectPhaseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectPhaseDtoConverter {

    @Autowired
    ProjectDtoConverter projectDtoConverter;

    public ProjectPhaseDto toDto(ProjectPhase projectPhase){
        ProjectPhaseDto projectPhaseDto = localConvertToDto(projectPhase);

        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projectPhase.getProjects()) {
            projectDtos.add(projectDtoConverter.localConvertToDto(project));
        }
        projectPhaseDto.setProjectsDtos(projectDtos);

        List<ProjectPhaseTemplateDto> projectPhaseTemplatesDtoes = new ArrayList<>();

        for (ProjectPhaseTemplate projectPhaseTemplate : projectPhase.getProjectPhaseTemplates()) {
            ProjectPhaseTemplateDto projectPhaseTemplateDto = new ProjectPhaseTemplateDto();
            projectPhaseTemplateDto.setId(projectPhaseTemplate.getId());
            projectPhaseTemplateDto.setName(projectPhaseTemplate.getName());
            projectPhaseTemplateDto.setUrl(projectPhaseTemplate.getUrl());
            projectPhaseTemplateDto.setDescription(projectPhaseTemplate.getDescription());
            projectPhaseTemplateDto.setDocument(projectPhaseTemplate.getDocument());
            projectPhaseTemplateDto.setDocumentName(projectPhaseTemplate.getDocumentName());

            projectPhaseTemplatesDtoes.add(projectPhaseTemplateDto);
        }
        projectPhaseDto.setProjectPhaseTemplates(projectPhaseTemplatesDtoes);

        return projectPhaseDto;
    }

    ProjectPhaseDto localConvertToDto(ProjectPhase projectPhase){
        ProjectPhaseDto projectPhaseDto = new ProjectPhaseDto();
        projectPhaseDto.setId(projectPhase.getId());
        projectPhaseDto.setName(projectPhase.getName());
        projectPhaseDto.setDescription(projectPhase.getDescription());

        return projectPhaseDto;

    }

    public ProjectPhase toEntity(ProjectPhaseDto projectPhaseDto){
        ProjectPhase projectPhase = localConvertToEntity(projectPhaseDto);

        List<Project> projects = new ArrayList<>();
        for (ProjectDto projectDto : projectPhaseDto.getProjectsDtos()){
            projects.add(projectDtoConverter.localConvertToEntity(projectDto));
        }
        projectPhase.setProjects(projects);

        List<ProjectPhaseTemplate> projectPhaseTemplates = new ArrayList<>();

        if ( projectPhaseDto.getProjectPhaseTemplates()!= null) {
            for (ProjectPhaseTemplateDto projectPhaseTemplateDto : projectPhaseDto.getProjectPhaseTemplates()) {
                ProjectPhaseTemplate projectPhaseTemplate = new ProjectPhaseTemplate();
                projectPhaseTemplate.setId(projectPhaseTemplateDto.getId());
                projectPhaseTemplate.setName(projectPhaseTemplateDto.getName());
                projectPhaseTemplate.setUrl(projectPhaseTemplateDto.getUrl());
                projectPhaseTemplate.setDescription(projectPhaseTemplateDto.getDescription());
                projectPhaseTemplate.setDocument(projectPhaseTemplateDto.getDocument());
                projectPhaseTemplate.setDocumentName(projectPhaseTemplateDto.getDocumentName());

                projectPhaseTemplates.add(projectPhaseTemplate);
            }
            projectPhase.setProjectPhaseTemplates(projectPhaseTemplates);
        }

        return projectPhase;
    }

    ProjectPhase localConvertToEntity(ProjectPhaseDto projectPhaseDto){
        ProjectPhase projectPhase = new ProjectPhase();
        projectPhase.setId(projectPhaseDto.getId());
        projectPhase.setName(projectPhaseDto.getName());
        projectPhase.setDescription(projectPhaseDto.getDescription());

        return projectPhase;
    }
}
