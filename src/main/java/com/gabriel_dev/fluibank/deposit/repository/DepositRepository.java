package com.gabriel_dev.fluibank.deposit.repository;

import com.gabriel_dev.fluibank.deposit.entity.DepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<DepositEntity, Long> {
}
