package com.packageBE.PMOStandard.service;

import com.packageBE.PMOStandard.dto.ProjectPhaseDto;

import java.util.List;

public interface IProjectPhaseService  {


    public List<ProjectPhaseDto> getAll();

    public ProjectPhaseDto getById(Long id);

    public void add(ProjectPhaseDto projectPhaseDto);

    public void delete(ProjectPhaseDto projectPhaseDto);

}
