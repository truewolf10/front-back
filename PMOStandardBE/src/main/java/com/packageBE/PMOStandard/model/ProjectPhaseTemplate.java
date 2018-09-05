package com.packageBE.PMOStandard.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "template")
public class ProjectPhaseTemplate {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;
    private String url;
    private String description;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] document;
    private String documentName;


    public ProjectPhaseTemplate() {
    }

    public ProjectPhaseTemplate(String name,
                                String url,
                                String description,
                                byte[] document,
                                String documentName) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.document = document;
        this.documentName = documentName;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
