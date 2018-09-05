package com.packageBE.PMOStandard.service.impl;

import com.packageBE.PMOStandard.dto.ProjectPhaseTemplateDto;
import com.packageBE.PMOStandard.model.ProjectPhaseTemplate;
import com.packageBE.PMOStandard.service.IProjectPhaseTemplateService;
import com.packageBE.PMOStandard.service.conv.ProjectPhaseTemplateConv;
import com.packageBE.PMOStandard.repository.IProjectPhaseTemplateRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectPhaseTemplateService implements IProjectPhaseTemplateService {

    private final IProjectPhaseTemplateRepository templateRepository;
    private final ProjectPhaseTemplateConv phaseTemplateConv;

    public ProjectPhaseTemplateService(IProjectPhaseTemplateRepository templateRepository,
                                       ProjectPhaseTemplateConv phaseTemplateConv) {
        this.templateRepository = templateRepository;
        this.phaseTemplateConv = phaseTemplateConv;
    }

    @Override
    public Long save(ProjectPhaseTemplateDto templateDto) {
        Optional<ProjectPhaseTemplate> optionalTemplate;

        if(templateDto.getId() == null) {
            optionalTemplate = Optional.empty();
        } else {
            optionalTemplate = templateRepository.findById(templateDto.getId());
        }

        ProjectPhaseTemplate template = optionalTemplate.orElseGet(ProjectPhaseTemplate::new);
        template = phaseTemplateConv.dtoToEntity(templateDto);

        return templateRepository.save(template).getId();
    }

    @Override
    public Boolean delete(Long id) {
        templateRepository.deleteById(id);
        return true;
    }

    @Override
    public ProjectPhaseTemplateDto getById(Long id) {
        ProjectPhaseTemplate template = templateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find template with ID: " + id));
        ProjectPhaseTemplateDto templateDto = phaseTemplateConv.entityToDto(template);
        return templateDto;
    }

    @Override
    public List<ProjectPhaseTemplateDto> getAll() {
        List<ProjectPhaseTemplate> templates = templateRepository.findAll();
        List<ProjectPhaseTemplateDto > templateDtos = new ArrayList<>();

        for(ProjectPhaseTemplate template : templates) {
            ProjectPhaseTemplateDto templateDto = phaseTemplateConv.entityToDto(template);

            templateDtos.add(templateDto);
        }

        return templateDtos;
    }
}
