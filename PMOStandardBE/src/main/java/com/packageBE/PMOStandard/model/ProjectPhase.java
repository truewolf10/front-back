package com.packageBE.PMOStandard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "phases")
public class ProjectPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<ProjectPhaseTemplate> projectPhaseTemplates;

//      @ManyToMany( fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,CascadeType.MERGE} , mappedBy = "phases")
    @ManyToMany(cascade = {CascadeType.PERSIST} , mappedBy = "phases")
    private List<Project> projects = new ArrayList<>();


    public ProjectPhase() {
    }

    public ProjectPhase(Long id, String name,String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public ProjectPhase(String name,String description, List<ProjectPhaseTemplate> projectPhaseTemplates) {
        this.name = name;
        this.description = description;
        this.projectPhaseTemplates = projectPhaseTemplates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectPhaseTemplate> getProjectPhaseTemplates() {
        return projectPhaseTemplates;
    }

    public void setProjectPhaseTemplates(List<ProjectPhaseTemplate> projectPhaseTemplates) {
        this.projectPhaseTemplates = projectPhaseTemplates;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
