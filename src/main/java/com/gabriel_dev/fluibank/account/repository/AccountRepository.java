package com.gabriel_dev.fluibank.account.repository;

import com.gabriel_dev.fluibank.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsByNumberAccount(String numberAccount);
}
