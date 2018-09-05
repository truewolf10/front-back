package com.packageBE.PMOStandard.service;

import com.packageBE.PMOStandard.dto.StatusInformationDto;

import java.util.List;

public interface IStatusInformationService {

    Long save(StatusInformationDto statusInformationDto);
    void delete(Long id);
    StatusInformationDto getById(Long id);
    List<StatusInformationDto> getAll();
}
