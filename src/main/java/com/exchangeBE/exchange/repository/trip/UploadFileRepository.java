package com.exchangeBE.exchange.repository.trip;

import com.exchangeBE.exchange.entity.trip.UploadFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFileEntity, Long> {
}
