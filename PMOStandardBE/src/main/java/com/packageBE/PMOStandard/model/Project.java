package com.packageBE.PMOStandard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @Size(max = 100)
    private java.lang.String name;

    @NotNull
    private Integer size;
    private String description;

    @ManyToMany(cascade ={CascadeType.PERSIST})
    private List<ProjectPhase> phases = new ArrayList<>();

    public Project(){}

    public Project(java.lang.String name, Integer size,String description) {
        this.name = name;
        this.size = size;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<ProjectPhase> getPhases() {
        return phases;
    }

    public void setPhases(List<ProjectPhase> phases) {
        this.phases = phases;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
