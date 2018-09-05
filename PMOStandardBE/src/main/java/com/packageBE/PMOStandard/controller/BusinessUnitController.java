package com.packageBE.PMOStandard.controller;

import com.packageBE.PMOStandard.service.IBusinessUnitService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/units")
public class BusinessUnitController {

    private final IBusinessUnitService unitService;

    public BusinessUnitController(IBusinessUnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping()
    List<String> getAllUnits() {
        return unitService.toList();
    }
}
