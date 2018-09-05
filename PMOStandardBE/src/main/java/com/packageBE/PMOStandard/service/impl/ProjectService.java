package com.packageBE.PMOStandard.service.impl;

import com.packageBE.PMOStandard.dto.ProjectDto;
import com.packageBE.PMOStandard.dto.ProjectPhaseDto;
import com.packageBE.PMOStandard.model.Project;
import com.packageBE.PMOStandard.service.IService;
import com.packageBE.PMOStandard.service.conv.ProjectDtoConverter;
import com.packageBE.PMOStandard.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IService<ProjectDto, Long> {

    private final IProjectRepository projectRepo;
    private final ProjectDtoConverter projectDtoConverter;
    private final ProjectPhaseService projectPhaseService;


    @Autowired
    public ProjectService(IProjectRepository projectRepo, ProjectDtoConverter projectDtoConverter, ProjectPhaseService projectPhaseService) {
        this.projectRepo = projectRepo;
        this.projectDtoConverter = projectDtoConverter;
        this.projectPhaseService = projectPhaseService;
    }


    @Override
    public void save(ProjectDto entity) {
//        Optional<Project> projectOptional;
//        if (entity.getId() != null) {
//            projectOptional = projectRepo.findById(entity.getId());
//            if (!projectOptional.isPresent()){
//                entity.setId(null);
//            }
//        }
        for (ProjectPhaseDto projectPhaseDto : entity.getPhasesDtos()){
            ProjectPhaseDto projectPhaseDtoLocal;
            if ((projectPhaseDtoLocal = projectPhaseService.getById(projectPhaseDto.getId())) != null){
                projectPhaseDto.setName(projectPhaseDtoLocal.getName());
            }
        }
        Project project = projectDtoConverter.toEntity(entity);
        projectRepo.save(project);
    }

    @Override
    public void remove(Long key) {
        projectRepo.deleteById(key);
    }


    @Override
    public Iterable<ProjectDto> getAll() {
        List<Project> projects = projectRepo.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects){
            projectDtos.add(projectDtoConverter.toDto(project));
        }
        return projectDtos;
    }

    @Override
    public ProjectDto getById(Long key) {
        Optional<Project> projectOptional = projectRepo.findById(key);
        if (projectOptional.isPresent()){
            Project project = projectOptional.get();
            return projectDtoConverter.toDto(project);
        }
        return null;
    }

    @Override
    public int count() {
        return projectRepo.findAll().size();
    }
}
