package com.packageBE.PMOStandard.dto;

import java.util.*;

public class ProjectPhaseDto {

    private Long id;
    private String name;
    private String description;
    private List<ProjectPhaseTemplateDto> projectPhaseTemplates;
    private List<ProjectDto> projectsDtos = new ArrayList<>();


    public ProjectPhaseDto() {
    }

    public ProjectPhaseDto(Long id, String name,String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProjectPhaseDto(Long id, String name,String description, List<ProjectPhaseTemplateDto> projectPhaseTemplates) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectPhaseTemplates = projectPhaseTemplates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectPhaseTemplateDto> getProjectPhaseTemplates() {
        return projectPhaseTemplates;
    }

    public void setProjectPhaseTemplates(List<ProjectPhaseTemplateDto> projectPhaseTemplates) {
        this.projectPhaseTemplates = projectPhaseTemplates;
    }

    public List<ProjectDto> getProjectsDtos() {
        return projectsDtos;
    }

    public void setProjectsDtos(List<ProjectDto> projectsDtos) {
        this.projectsDtos = projectsDtos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
