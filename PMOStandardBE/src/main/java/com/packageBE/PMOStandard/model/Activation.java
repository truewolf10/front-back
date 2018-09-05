package com.packageBE.PMOStandard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Activation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean isActivated;

    @NotNull
    private String generatedId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Activation() {
    }

    public Activation(boolean isActivated, String generatedId) {
        this.isActivated = isActivated;
        this.generatedId = generatedId;
    }

    public Activation(String generatedId) {
        this.generatedId = generatedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
