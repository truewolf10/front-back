package com.packageBE.PMOStandard.repository;

import com.packageBE.PMOStandard.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long> {

}
