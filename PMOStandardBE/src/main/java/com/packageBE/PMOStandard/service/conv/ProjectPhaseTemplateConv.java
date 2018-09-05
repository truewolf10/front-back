package com.packageBE.PMOStandard.service.conv;

import com.packageBE.PMOStandard.dto.ProjectPhaseTemplateDto;
import com.packageBE.PMOStandard.model.ProjectPhaseTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProjectPhaseTemplateConv {

    public ProjectPhaseTemplateDto entityToDto(ProjectPhaseTemplate template) {
        ProjectPhaseTemplateDto templateDto = new ProjectPhaseTemplateDto();
        templateDto.setId(template.getId());
        templateDto.setName(template.getName());
        templateDto.setUrl(template.getUrl());
        templateDto.setDocument(template.getDocument());
        templateDto.setDocumentName(template.getDocumentName());
        templateDto.setDescription(template.getDescription());

        return templateDto;
    }

    public ProjectPhaseTemplate dtoToEntity(ProjectPhaseTemplateDto templateDto) {
        ProjectPhaseTemplate template = new ProjectPhaseTemplate();
        template.setId(templateDto.getId());
        template.setName(templateDto.getName());
        template.setUrl(templateDto.getUrl());
        template.setDocument(templateDto.getDocument());
        template.setDocumentName(templateDto.getDocumentName());
        template.setDescription(templateDto.getDescription());

        return template;
    }
}
