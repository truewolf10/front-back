package com.packageBE.PMOStandard.service;

import com.packageBE.PMOStandard.dto.ProjectPhaseTemplateDto;

import java.util.List;

public interface IProjectPhaseTemplateService {

    Long save(ProjectPhaseTemplateDto templateDto);

    Boolean delete(Long id);

    ProjectPhaseTemplateDto getById(Long id);

    List<ProjectPhaseTemplateDto> getAll();
}
