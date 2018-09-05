package com.packageBE.PMOStandard.repository;

import com.packageBE.PMOStandard.model.StatusInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusInformationRepo extends JpaRepository<StatusInformation, Long> {
}
