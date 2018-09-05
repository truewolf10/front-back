package com.packageBE.PMOStandard.service.conv;

import com.packageBE.PMOStandard.dto.StatusInformationDto;
import com.packageBE.PMOStandard.model.StatusInformation;
import org.springframework.stereotype.Component;

@Component
public class StatusInformationConverter {

    public StatusInformationDto toDto(StatusInformation statusInformation){
        StatusInformationDto statusInformationDto = new StatusInformationDto();
        statusInformationDto.setId(statusInformation.getId());
        statusInformationDto.setIdPhase(statusInformation.getIdPhase());
        statusInformationDto.setIdProject(statusInformation.getIdProject());
        statusInformationDto.setIdTemplate(statusInformation.getIdTemplate());
        statusInformationDto.setStatus(statusInformation.getStatus());

        return statusInformationDto;
    }

    public StatusInformation toEntity(StatusInformationDto statusInformationDto){
        StatusInformation statusInformation = new StatusInformation();
        statusInformation.setId(statusInformationDto.getId());
        statusInformation.setIdPhase(statusInformationDto.getIdPhase());
        statusInformation.setIdProject(statusInformationDto.getIdProject());
        statusInformation.setIdTemplate(statusInformationDto.getIdTemplate());
        statusInformation.setStatus(statusInformationDto.getStatus());

        return statusInformation;
    }

}
