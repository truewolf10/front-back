package com.packageBE.PMOStandard.repository;

import com.packageBE.PMOStandard.model.Activation;
import com.packageBE.PMOStandard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActivationRepository extends JpaRepository<Activation, Long> {

    Activation findByUser(User user);
}
