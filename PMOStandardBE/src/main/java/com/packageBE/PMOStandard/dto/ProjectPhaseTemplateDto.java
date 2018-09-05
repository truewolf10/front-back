package com.packageBE.PMOStandard.dto;

public class ProjectPhaseTemplateDto {
    private Long id;
    private String name;
    private String url;
    private byte[] document;
    private String documentName;
    private String description;

    public ProjectPhaseTemplateDto() {
    }

    public ProjectPhaseTemplateDto(Long id, String name, String url,String description) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public ProjectPhaseTemplateDto(Long id,
                                   String name,
                                   String url,
                                   String description,
                                   byte[] document,
                                   String documentName) {
        this.id = id;
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
