package com.packageBE.PMOStandard.dto;

import java.util.ArrayList;
import java.util.List;

public class ProjectDto {

    private Long id;
    private java.lang.String name;
    private Integer size;
    private String description;
    private List<ProjectPhaseDto> phasesDtos = new ArrayList<>();

    public ProjectDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<ProjectPhaseDto> getPhasesDtos() {
        return phasesDtos;
    }

    public void setPhasesDtos(List<ProjectPhaseDto> phasesDtos) {
        this.phasesDtos = phasesDtos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
