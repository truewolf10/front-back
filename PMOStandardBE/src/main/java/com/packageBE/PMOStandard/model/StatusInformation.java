package com.packageBE.PMOStandard.model;

import com.packageBE.PMOStandard.model.enumeration.StatusFile;
import javax.persistence.*;

@Entity
@Table(name="statusInformation")
public class StatusInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String idProject;

    private String idPhase;

    private String idTemplate;
    private StatusFile status;

    public StatusInformation() {
    }

    public StatusInformation(String idProject, String idPhase, String idTemplate, StatusFile status) {
        this.idProject = idProject;
        this.idPhase = idPhase;
        this.idTemplate = idTemplate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(String idPhase) {
        this.idPhase = idPhase;
    }

    public String getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(String idTemplate) {
        this.idTemplate = idTemplate;
    }

    public StatusFile getStatus() {
        return status;
    }

    public void setStatus(StatusFile status) {
        this.status = status;
    }
}


