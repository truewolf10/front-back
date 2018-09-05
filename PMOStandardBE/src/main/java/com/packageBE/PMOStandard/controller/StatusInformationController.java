package com.packageBE.PMOStandard.controller;

import com.packageBE.PMOStandard.dto.StatusInformationDto;
import com.packageBE.PMOStandard.service.IStatusInformationService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/status")
public class StatusInformationController {

    private IStatusInformationService iStatusInformationService;

    public StatusInformationController(IStatusInformationService iStatusInformationService) {
        this.iStatusInformationService = iStatusInformationService;
    }

    @GetMapping()
    public Iterable<StatusInformationDto> getAll(){
        return this.iStatusInformationService.getAll();
    }

    @GetMapping("/{status_id}")
    public StatusInformationDto getById(@PathVariable("status_id") Long statusId){
        return this.iStatusInformationService.getById(statusId);
    }

    @PostMapping()
    public void addStatus(@RequestBody StatusInformationDto statusInformationDto){
        this.iStatusInformationService.save(statusInformationDto);
    }

    @PutMapping()
    public Long update(@RequestBody StatusInformationDto statusInformationDto){
        this.iStatusInformationService.save(statusInformationDto);

        Long index = 0L;
        for(StatusInformationDto statusDto : getAll()) {
            index = statusDto.getId();
        }

        return index;
    }

    @DeleteMapping("/{project_id}")
    public void delete(@PathVariable("project_id") Long statusId){
        this.iStatusInformationService.delete(statusId);
    }


}
