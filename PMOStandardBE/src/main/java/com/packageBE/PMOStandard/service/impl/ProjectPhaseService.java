package com.packageBE.PMOStandard.service.impl;

import com.packageBE.PMOStandard.dto.ProjectPhaseDto;
import com.packageBE.PMOStandard.model.ProjectPhase;
import com.packageBE.PMOStandard.service.IProjectPhaseService;
import com.packageBE.PMOStandard.service.conv.ProjectPhaseDtoConverter;
import com.packageBE.PMOStandard.repository.IProjectPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectPhaseService implements IProjectPhaseService {
    private final IProjectPhaseRepository IProjectPhaseRepository;
    private final ProjectPhaseDtoConverter projectPhaseDtoConverter;

    @Autowired
    public ProjectPhaseService(IProjectPhaseRepository IProjectPhaseRepository, ProjectPhaseDtoConverter projectPhaseDtoConverter) {
        this.IProjectPhaseRepository = IProjectPhaseRepository;
        this.projectPhaseDtoConverter = projectPhaseDtoConverter;
    }

    @Override
    public List<ProjectPhaseDto> getAll(){
        List<ProjectPhase> projectPhases = IProjectPhaseRepository.findAll();
        List<ProjectPhaseDto> projectPhaseDtos = new ArrayList<>();
        for (ProjectPhase projectPhase : projectPhases){
            ProjectPhaseDto projectPhaseDto = projectPhaseDtoConverter.toDto(projectPhase);
            projectPhaseDtos.add(projectPhaseDto);
        }
        return projectPhaseDtos;
    }

    @Override
    public ProjectPhaseDto getById(Long id){
        Optional<ProjectPhase> optionalProjectPhase = IProjectPhaseRepository.findById(id);
        if (optionalProjectPhase.isPresent()){
            ProjectPhase projectPhase = optionalProjectPhase.get();
            ProjectPhaseDto projectPhaseDto = projectPhaseDtoConverter.toDto(projectPhase);
            return projectPhaseDto;
        }
        return null;
    }

    @Override
    public void add(ProjectPhaseDto projectPhaseDto){
        IProjectPhaseRepository.save(projectPhaseDtoConverter.toEntity(projectPhaseDto));
    }

    @Override
    public void delete(ProjectPhaseDto projectPhaseDto){
        IProjectPhaseRepository.delete(projectPhaseDtoConverter.toEntity(projectPhaseDto));
    }
}
