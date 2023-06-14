package com.fetch.receiptProcessor.Repository;

import com.fetch.receiptProcessor.Entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, Long> {
}