package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.UploadFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFileEntity, Long> {
}
