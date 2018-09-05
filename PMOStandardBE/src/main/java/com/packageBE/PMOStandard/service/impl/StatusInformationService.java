package com.packageBE.PMOStandard.service.impl;


import com.packageBE.PMOStandard.dto.StatusInformationDto;
import com.packageBE.PMOStandard.model.StatusInformation;
import com.packageBE.PMOStandard.service.IStatusInformationService;
import com.packageBE.PMOStandard.service.conv.StatusInformationConverter;
import com.packageBE.PMOStandard.repository.IStatusInformationRepo;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatusInformationService implements IStatusInformationService {
    private final IStatusInformationRepo statusInformationRepo;
    private final StatusInformationConverter statusInformationConverter;


    public StatusInformationService(IStatusInformationRepo statusInformationRepo, StatusInformationConverter statusInformationConverter) {
        this.statusInformationRepo = statusInformationRepo;
        this.statusInformationConverter = statusInformationConverter;
    }


    @Override
    public Long save(StatusInformationDto statusInformationDto){
        StatusInformation statusInformation = statusInformationConverter.toEntity(statusInformationDto);
        return statusInformationRepo.save(statusInformation).getId();
    }

    @Override
    public void delete(Long id){ statusInformationRepo.deleteById(id);}

    @Override
    public StatusInformationDto getById(Long id){
        StatusInformation statusInformation = statusInformationRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find status with ID: " + id));
        return statusInformationConverter.toDto(statusInformation);
    }

    @Override
    public List<StatusInformationDto> getAll() {
        List<StatusInformation> statusInformations = statusInformationRepo.findAll();
        List<StatusInformationDto> statusInformationDto = new ArrayList<>();
        for(StatusInformation statusInformation : statusInformations) {
            statusInformationDto.add(statusInformationConverter.toDto(statusInformation));
        }

        return statusInformationDto;
    }

}
