package com.packageBE.PMOStandard.repository;

import com.packageBE.PMOStandard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
