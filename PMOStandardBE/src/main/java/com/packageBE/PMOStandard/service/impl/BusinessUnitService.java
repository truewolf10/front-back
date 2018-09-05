package com.packageBE.PMOStandard.service.impl;

import com.packageBE.PMOStandard.model.enumeration.BusinessUnit;
import com.packageBE.PMOStandard.service.IBusinessUnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BusinessUnitService implements IBusinessUnitService {

    @Override
    public List<String> toList() {
        return Stream.of(BusinessUnit.values())
                .map(BusinessUnit::name)
                .collect(Collectors.toList());
    }
}
