package com.gabriel_dev.fluibank.pixKey.repository;

import com.gabriel_dev.fluibank.pixKey.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PixKeyRepository extends JpaRepository<PixKeyEntity, Long> {

    PixKeyEntity findByKey(String key);

    List<PixKeyEntity> findAllByAccount_Id(Long accountId);
}
